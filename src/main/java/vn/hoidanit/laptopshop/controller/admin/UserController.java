package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUserByEmail("1@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("tuananh", "test");
        model.addAttribute("haha", "from controller with model");
        return "hello";
    }

    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") @Valid User tuananh,
            BindingResult newUserBindingResult,
            @RequestParam("tuananhFile") MultipartFile file) {

        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        // validate

        if (newUserBindingResult.hasErrors()) {
            return "admin/user/create";
        }

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(tuananh.getPassword());
        tuananh.setAvatar(avatar);
        tuananh.setPassword(hashPassword);
        tuananh.setRole(this.userService.getRoleByName(tuananh.getRole().getName()));
        this.userService.hanldeSaveUser(tuananh);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user")
    public String getTableUsers(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                // convert from String to int
                page = Integer.parseInt(pageOptional.get());
            } else {
                // page = 1
            }

        } catch (Exception e) {
            // page = 1
        }
        Pageable pageable = PageRequest.of(page - 1, 1);
        Page<User> users = this.userService.getAllUser(pageable);
        List<User> listUsers = users.getContent();
        model.addAttribute("users1", listUsers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", users.getTotalPages());
        return "admin/user/show";
    }

    @RequestMapping(value = "/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    @RequestMapping(value = "/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        model.addAttribute("id", id);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User tuananh,
            @RequestParam("tuananhFile") MultipartFile file) {
        User currentUser = this.userService.getUserById(tuananh.getId());
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        if (currentUser != null) {
            currentUser.setAddress(tuananh.getAddress());
            currentUser.setFullName(tuananh.getFullName());
            currentUser.setPhone(tuananh.getPhone());
            currentUser.setAvatar(avatar);
            currentUser.setRole(this.userService.getRoleByName(tuananh.getRole().getName()));
            this.userService.hanldeSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        // User user = new User();
        // user.setId(id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User tuananh) {
        this.userService.deleteUserById(tuananh.getId());
        return "redirect:/admin/user";
    }
}

// @RestController
// public class UserController {
// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("/")

// public String getHomePage() {
// return this.userService.handleHello();
// }
// }