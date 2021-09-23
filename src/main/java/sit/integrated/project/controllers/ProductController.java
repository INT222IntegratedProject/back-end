package sit.integrated.project.controllers;



import org.springframework.web.bind.annotation.*;
import sit.integrated.project.exceptions.ExceptionResponse;
import sit.integrated.project.exceptions.ProductsException;
import sit.integrated.project.models.Products;
import sit.integrated.project.repositories.ProductsRepositories;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping("/Products")
@CrossOrigin
public class ProductController {
@Autowired
    private ProductsRepositories productsRepositories;
    private  Products Product;



   @GetMapping("/GetProducts")
   public List<Products> getAllProduct(){return productsRepositories.findAll(); }

    @GetMapping("/GetProducts/{id}")
    public  Products getProductById(@PathVariable int id){ return  productsRepositories.findById(id).orElse(null); }

     @PostMapping("/Create")
    public Products createProduct(@RequestBody Products products){
         List<Products> listprod = productsRepositories.findAll();
         Products[] productsArrays = new Products[listprod.size()];
         listprod.toArray(productsArrays);
         for(int i = 0 ; i < productsArrays.length; i++ ) {
             if (productsArrays[i].getProductName().equals(products.getProductName())) {
                 throw new ProductsException(ExceptionResponse.ERROR_CODE.ITEM_ALREADY_EXIST ,"NameProduct is Duplicate");
             }
         }
         products.setProductId(productsRepositories.productLatestId() + 1);
         productsRepositories.save(products);
         return products;
   }


    @PutMapping("/Edit/{id}")
    public Products editProduct(@RequestBody Products products,@PathVariable int id){
        if (hasFoundId(id) && (id == products.getProductId())) {
            productsRepositories.save(products);
            return products;
        }
        else throw  new ProductsException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST , "The product DOES NOT EXIST");

    }

    @DeleteMapping("/Delete/{id}")
    public void deleteProduct(@PathVariable int id){
        if (hasFoundId(id)) {
            productsRepositories.deleteById(id);
        }
        else  throw new ProductsException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST,"The product DOES NOT EXIST");
    }


    public boolean hasFoundId(int id){
        List<Products> products = productsRepositories.findAll();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getProductId() == id){
                return true;
            }
        }
        return false;
    }
}
