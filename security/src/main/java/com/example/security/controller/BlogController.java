package com.example.security.controller;

import com.example.security.model.Blog.Blog;
import com.example.security.model.Blog.BlogDTO;
import com.example.security.model.Blog.Category;
import com.example.security.model.Blog.CommentBlog;
import com.example.security.model.User;
import com.example.security.service.BlogService.IBlogService;
import com.example.security.service.BlogService.ICommentBlogService;
import com.example.security.service.CategoryService.ICategoryService;
import com.example.security.service.User.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IBlogService iBlogService;
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private ICommentBlogService iCommentBlogService;

    @GetMapping("/")
    public ModelAndView detail(@RequestParam(defaultValue = "0") int page) {

        ModelAndView mV = new ModelAndView("/detail");
        return mV;
    }
    @RequestMapping("/showListBlog")
    @GetMapping
    public ModelAndView showListAfterLogin(@RequestParam(defaultValue = "0") int page,Principal principal, Model model) {
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(page, 99, sort);
        Page<Blog> blogs = iBlogService.findAll(pageable);
        int size = blogs.getTotalPages();
        List<Integer> listPage = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            listPage.add(i);
        }
        User user = new User();
        String email = principal.getName();
        user.setEmail(email);
        model.addAttribute("blog", new Blog());
        model.addAttribute("categoryList", iCategoryService.finAll());
        ModelAndView mV = new ModelAndView("/home");
        mV.addObject("pages", listPage);
        mV.addObject("cate", iCategoryService.finAll());
        mV.addObject("list", blogs);
        mV.addObject("user",user);
        return mV;
    }

    @GetMapping("/showCreate")
    public String showCreate(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categoryList", iCategoryService.finAll());
        return "create";
    }

    @GetMapping("/{id}/showFormEdit")
    public ModelAndView showEdit(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("edit");
        mv.addObject("blog",iBlogService.findOne(id));
        mv.addObject("category",iCategoryService.finAll());
        return mv;
    }

    @GetMapping("/{id}/viewBlog")
    public ModelAndView viewBlog(@PathVariable("id") Long id, Principal principal){
        ModelAndView mv = new ModelAndView("viewBlog");
        Blog blog = iBlogService.findOne(id);
        User userTemp = new User();
        String email = principal.getName();
        userTemp.setEmail(email);

        //them luot view
        int temp = blog.getViewBlog();
        temp+=1;
        blog.setViewBlog(temp);
        iBlogService.save(blog);

        mv.addObject("user",userTemp);
        mv.addObject("comment",iCommentBlogService.findAllCommentBlogs());
        mv.addObject("blog",blog);
        mv.addObject("category",iCategoryService.finAll());
        return mv;
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

    @GetMapping("/showRegister")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @GetMapping("/{id}/showDelete")
    public String showDelete(@PathVariable("id") Long id,Model model) {
        model.addAttribute("blog", iBlogService.findOne(id));
        return "delete";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id,Model model){
        iBlogService.delete(id);
        return "redirect:/showListBlog";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("blog") Blog blog,Model model){
        iBlogService.save(blog);
        return "redirect:/"+blog.getId()+"/viewBlog";
    }

    @PostMapping("/createBlog")
    public String create(@Valid @ModelAttribute("blog") BlogDTO blog, BindingResult bindingResult, Model model, Principal principal) {
        new BlogDTO().validate(blog, bindingResult);
        String email = principal.getName();
//        if (bindingResult.hasErrors()) {
//            model.addAttribute(iCategoryService.finAll());
//            return "createBlog";
//        }
        Blog s = new Blog();
        Category category = iCategoryService.findById(blog.getCategory());
        User user = iUserService.findUserByEmail(email);
        // Chuyển đổi dữ liệu từ DTO -> Entity
        BeanUtils.copyProperties(blog, s);
        s.setLikeBlog(0);
        s.setViewBlog(0);
        s.setUser(user);
        s.setAuthor(email);
        s.setCategory(category);
        iBlogService.addNewBlog(s);
        return "redirect:/showListBlog";
    }
}
