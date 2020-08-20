package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Bill;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.UserRepresentation;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.BillService;
import ru.geekbrains.domodel.services.api.MeterService;
import ru.geekbrains.domodel.services.api.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.geekbrains.domodel.entities.constants.Messages.PASSWORD_MISMATCH;

/**
 * Контроллер модуля профиля пользователя
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    // Список необходимых сервисов
    private final UserService userService;
    private final AccountService accountService;
    private final BillService billService;
    private final MeterService meterService;

    @Autowired
    public ProfileController(UserService userService,
                             AccountService accountService,
                             BillService billService,
                             MeterService meterService) {
        this.userService = userService;
        this.accountService = accountService;
        this.billService = billService;
        this.meterService = meterService;
    }

    /**
     * Перехват запроса на чтение профиля пользователя
     */
    // TODO привести в нормальный вид все методы
    @GetMapping("")
    public String getProfilePage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        User user = userService.getUserByUsername(principal.getName());
        if (user != null) {
            model.addAttribute("user", user);
        }
        List<Account> accounts = accountService.getAccountsByUser(user);
        model.addAttribute("accounts", accounts);
        if (accounts.size() != 0) {
            Account account = accounts.get(0);
            model.addAttribute("account", account);
            // TODO возможно лучше все остальные данные сразу подтягивать через аккаунт
            model.addAttribute("calculatedBills", billService.getAllBillsByAccount(account)
                    .stream().filter(Bill::isCalculated).collect(Collectors.toList()));
            model.addAttribute("fixedBills", billService.getAllBillsByAccount(account)
                    .stream().filter(b -> !b.isCalculated()).collect(Collectors.toList()));
            // TODO еще здесь должны быть показания счетчиков
            model.addAttribute("meters", meterService.getAllMetersByAccount(account));
        } else {
            model.addAttribute("calculatedBills", new ArrayList<>());
            model.addAttribute("fixedBills", new ArrayList<>());
            model.addAttribute("meters", new ArrayList<>());
        }
        return "pages/profile";
    }

    /**
     * Перехват запроса на изменение профиля пользователя
     */
    @GetMapping("/change")
    public String getProfileChangePage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
            model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        }
        model.addAttribute("userData", new UserRepresentation());
        return "pages/change_profile";
    }

    /**
     * Перехват запроса на изменение профиля пользователя
     */
    @PostMapping("/change")
    public String changeUserProfile(@Valid @ModelAttribute("userData") UserRepresentation userData,
                                    BindingResult bindingResult,
                                    Model model,
                                    Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        if (bindingResult.hasErrors()) {
            return "pages/change_profile";
        }

        if (!userData.getPassword().equals(userData.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "", PASSWORD_MISMATCH);
            return "pages/change_profile";
        }

        userService.updateUser(userData, user);
        return "redirect:/change";
    }
}
