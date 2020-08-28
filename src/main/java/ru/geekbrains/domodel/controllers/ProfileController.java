package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.UserRepresentation;
import ru.geekbrains.domodel.services.api.UserService;

import javax.validation.Valid;
import java.security.Principal;

import static ru.geekbrains.domodel.entities.constants.Messages.PASSWORD_MISMATCH;

/**
 * Контроллер модуля профиля пользователя
 */
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    // Адреса шаблонов страниц
    private static final String PROFILE_EDIT_FORM = "profile/profile_edit";
    private static final String PROFILE_USER_FORM = "profile/profile_user";

    // Список необходимых сервисов
    private final UserService userService;

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
//        model.addAttribute("userData", new UserRepresentation());
//        List<Account> accounts = accountService.getAccountsByUser(user);
//        model.addAttribute("accounts", accounts);
//        if (accounts.size() != 0) {
//            Account account = accounts.get(0);
//            model.addAttribute("account", account);
//            // TODO возможно лучше все остальные данные сразу подтягивать через аккаунт
//            List<Bill> billList = billService.getAllBillsByAccount(account);
//            model.addAttribute("calculatedBills", billList
//                    .stream().filter(b -> b.getType() == BillType.MEMBERSHIP_FEE_CALCULATED ||
//                            b.getType() == BillType.OTHER_FEE_CALCULATED ).collect(Collectors.toList()));
//            model.addAttribute("fixedBills", billList
//                    .stream().filter(b -> b.getType() == BillType.MEMBERSHIP_FEE_FIXED ||
//                            b.getType() == BillType.OTHER_FEE_FIXED).collect(Collectors.toList()));
//            model.addAttribute("meterBills", billList
//                    .stream().filter(b -> b.getType() == BillType.METERS).collect(Collectors.toList()));
//            model.addAttribute("meters", meterService.getAllMetersByAccount(account));
//        } else {
//            model.addAttribute("calculatedBills", new ArrayList<>());
//            model.addAttribute("fixedBills", new ArrayList<>());
//            model.addAttribute("meters", new ArrayList<>());
//        }
        return PROFILE_USER_FORM;
    }

    /**
     * Перехват запроса на изменение профиля пользователя
     */
    @PostMapping("/edit")
    public String changeUserProfile(@Valid @ModelAttribute("userData") UserRepresentation userData,
                                    BindingResult bindingResult,
                                    Model model,
                                    Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        if (bindingResult.hasErrors()) {
            return PROFILE_EDIT_FORM;
        }

        if (!userData.getPassword().equals(userData.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "", PASSWORD_MISMATCH);
            return PROFILE_EDIT_FORM;
        }

        userService.updateUser(userData, user);
        return "redirect:" + PROFILE_EDIT_FORM;
    }

    /**
     * Перехват запроса на изменение ФИО пользователя
     */
    @PostMapping("/edit/name")
    public String editUserNames(@Valid @ModelAttribute("user") User user,
                                    BindingResult bindingResult,
                                    Principal principal) {
        if (user.getLastName() != null ||
                user.getFirstName() != null ||
                user.getPatronymic() != null) {
//            bindingResult.rejectValue("password", "", PASSWORD_MISMATCH);
            userService.editUser(user, principal.getName());
        }
        return "redirect:" + PROFILE_EDIT_FORM;
    }
}
