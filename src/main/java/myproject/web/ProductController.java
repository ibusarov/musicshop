package myproject.web;

import myproject.model.binding.ProductAddBindingModel;
import myproject.model.entity.Product;
import myproject.model.service.ProductService;
import myproject.service.ProductServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

private final ModelMapper modelMapper;
private final ProductService productService;

    public ProductController(ModelMapper modelMapper, ProductService productService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @GetMapping("/lists")
    public String getProductList(Model productList){

        productList.addAttribute("productList",productService.productList());
        return "product-list";
    }

    @GetMapping("/lists/product/{id}")
    public String getProductInfo(@PathVariable("id") String id, Model model){

        model.addAttribute("prod",this.productService.findById(id));

        return "product-details";
    }


    @GetMapping("/add")
    public String add(Model model){

        if (!model.containsAttribute("productAddBindingModel")){
            model.addAttribute("productAddBindingModel",new ProductAddBindingModel());
        }
        return "add-product";
    }


    @PostMapping("/add")
    public String confirmItem(@Valid @ModelAttribute("productAddBindingModel")
                                      ProductAddBindingModel productAddBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("productAddBindingModel",productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel",
                    bindingResult);


            return "redirect:add";
        }



        this.productService.addProduct(this.modelMapper
                .map(productAddBindingModel, ProductServiceModel.class));



        return "redirect:/";
    }

    @GetMapping("/adminlist")
    public String getProductListAdmin(Model productListAdmin){

        productListAdmin.addAttribute("productListAdmin",productService.productList());
        return "admin-list-product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id)
    {
        this.productService.delete(id);

        return "redirect:/";
    }


}
