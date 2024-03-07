package com.example.security.controller;

import com.example.security.common.EncryptPasswordUtils;
import com.example.security.model.Blog.Blog;
import com.example.security.model.Blog.BlogDTO;
import com.example.security.model.Blog.Category;
import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import com.example.security.service.BlogService.IBlogService;
import com.example.security.service.User.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IBlogService iBlogService;@Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    IUserService iUserService;
//    @GetMapping("/")
//    public ModelAndView showList(@RequestParam(defaultValue = "0") int page) {
//        Sort sort = Sort.by("name").ascending();
//        Pageable pageable = PageRequest.of(page, 99, sort);
//        Page<Blog> blogs = iBlogService.findAll(pageable);
//        int size = blogs.getTotalPages();
//        List<Integer> listPage = new ArrayList<>();
//        for (int i = 1; i <= size; i++) {
//            listPage.add(i);
//        }
//        ModelAndView mV = new ModelAndView("/login");
//        mV.addObject("pages", listPage);
//        mV.addObject("list", blogs);
//        return mV;
//    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id")Long id,Model model){
        ModelAndView mv = new ModelAndView("redirect:/user/showList");
        Optional<User> user = iUserService.findUserById(id);
        user.get().setStatus(false);
        iUserService.save(user.get());
        return mv;
    }
    @GetMapping
    @RequestMapping("/showMylistBlog")
    public String showMyListBlog(Model model,Principal principal){
        String email = principal.getName();
        User user = iUserService.findUserByEmail(email);
        List<Blog> blogs = user.getBlogs();
        model.addAttribute("user",user);
        model.addAttribute("blogs",blogs);
        return "/showMyListBlog";
    }

    @GetMapping("/showList")
    private ModelAndView showListUser(@RequestParam(defaultValue = "0") int page){
        Sort sort = Sort.by("email").ascending();
        Pageable pageable = PageRequest.of(page, 99, sort);
        Page<User> userList = iUserService.findAlll(pageable);
        ModelAndView mv = new ModelAndView("/userList");
        List<Role> roles = roleRepository.findAll();
//        List<User> userList = iUserService.findAll();
        mv.addObject("userList",userList);
        mv.addObject("blog",iBlogService.findAlll());
        mv.addObject("role",roles);
        return mv;
    }

//    @PostMapping("/createBlog")
//    public String create(@Valid @ModelAttribute("blog") BlogDTO blog, BindingResult bindingResult, Model model, Principal principal) {
//        new BlogDTO().validate(blog, bindingResult);
//        String email = principal.getName();
////        if (bindingResult.hasErrors()) {
////            model.addAttribute(iCategoryService.finAll());
////            return "createBlog";
////        }
//        Blog s = new Blog();
//        Category category = iCategoryService.findById(blog.getCategory());
//        User user = iUserService.findUserByEmail(email);
//        // Chuyển đổi dữ liệu từ DTO -> Entity
//        BeanUtils.copyProperties(blog, s);
//        s.setUser(user);
//        s.setAuthor(email);
//        s.setCategory(category);
//        iBlogService.addNewBlog(s);
//        return "redirect:/showListBlog";
//    }
@GetMapping("/showRegister")
public String showRegister(Model model) {
    model.addAttribute("user", new User());
    return "register";
}
@PostMapping("/registration")
    public String onApplicationEvent(@ModelAttribute("user") User user, BindingResult bindingResult,Model model) {
        //them admin
        String email = user.getEmail();
        String pass = user.getPassword();
        if (userRepository.findByEmail(email) == null) {
            User admin = new User();
            admin.setEmail(email);
            // mã hóa mật khẩu
            admin.setPassword(EncryptPasswordUtils.EncryptPasswordUtils(pass));
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName("ROLE_USER"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }else{

        }
        return "redirect:/";
    }

}
