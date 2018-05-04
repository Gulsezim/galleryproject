package com.gallery.galleryproject.controllers;

import com.gallery.galleryproject.models.Customer;
import com.gallery.galleryproject.reposotories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/demo")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository ;

    @RequestMapping("/show")
    public String showForm(Model model){
        model.addAttribute("customer",new Customer());
        return "inp";
    }

    @GetMapping("/add")
    public @ResponseBody String addCustomer(@RequestParam("firstname") String firstName,
                                          @RequestParam("lastname") String lastName,
                                          @RequestParam("email") String email){
        Customer customer = new Customer(firstName, lastName, email) ;
        customerRepository.save(customer);
        return "saved" ;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Customer> allCustomers(){
        return customerRepository.findAll() ;
    }

    @GetMapping("/all2")
    public String allCustomers2(Model model){
        List<Customer> customers = (List<Customer>) customerRepository.findAll() ;
        model.addAttribute("customers", customers) ;
        return "customers" ;
    }

    @RequestMapping(value = "/deleteContact",method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd){
        customerRepository.deleteById(idd);
        return new ModelAndView("redirect:/demo/all2");
    }
}
