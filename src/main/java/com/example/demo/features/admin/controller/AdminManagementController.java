package com.example.demo.features.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.features.admin.model.Admin;
import com.example.demo.features.admin.service.AdminServices;
import com.example.demo.features.order.model.Orders;
import com.example.demo.features.order.service.OrderServices;
import com.example.demo.features.product.model.Product;
import com.example.demo.features.product.service.ProductServices;
import com.example.demo.features.user.model.User;
import com.example.demo.features.user.service.UserServices;

@Controller
@RequestMapping("/admin") // sets base route for admin http requests
public class AdminManagementController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private AdminServices adminServices;
    @Autowired
    private ProductServices productServices;
    @Autowired
    private OrderServices orderServices;

    @GetMapping("/services")
    public String returnBack(Model model) {
        List<User> users = this.userServices.getAllUser();
        List<Admin> admins = this.adminServices.getAll();
        List<Product> products = this.productServices.getAllProducts();
        List<Orders> orders = this.orderServices.getOrders();

        model.addAttribute("users", users);
        model.addAttribute("admins", admins);
        model.addAttribute("products", products);
        model.addAttribute("orders", orders);

        return "Admin_Page";
    }

    @GetMapping("/addAdmin")
    public String addAdminPage() { return "Add_Admin"; }

    @PostMapping("/addingAdmin")
    public String addAdmin(@ModelAttribute Admin admin) {
        this.adminServices.addAdmin(admin);
        return "redirect:/admin/services";
    }

    @GetMapping("/updateAdmin/{adminId}")
    public String update(@PathVariable("adminId") int id, Model model)
    {
        Admin admin = this.adminServices.getAdmin(id);
        model.addAttribute("admin", admin);
        return "Update_Admin";
    }
    @GetMapping("/updatingAdmin/{id}")
    public String updateAdmin(@ModelAttribute Admin admin,@PathVariable("id") int id)
    {
        this.adminServices.update(admin, id);
        return "redirect:/admin/services";
    }
    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable("id") int id)
    {
        this.adminServices.delete(id);
        return "redirect:/admin/services";
    }

    /// /////////////////////////////////////////////////////////////////////

    // --- Product CRUD Operations ---
    @GetMapping("/addProduct")
    public String addProduct() {
        return "Add_Product";
    }


    // ... all other Product update/delete/get methods remain here.

    // --- User CRUD Operations ---
    @GetMapping("/addUser")
    public String addUser() {
        return "Add_User";
    }
    // ... all other User update/delete/get methods remain here.
}