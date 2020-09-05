package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.BillDto;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.entities.Bill;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
import ru.geekbrains.domodel.services.api.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Временный контроллер web-приложения для совместимости со старой версией фронта приложения
 */
@Controller
@RequiredArgsConstructor
public class TemporaryController {
/*
    // Имена шаблонов страниц
    private static final String REGISTER_FORM = "temp/register";
    private static final String ACCOUNTS_ADMIN_FORM = "profile/accounts_admin";
    private static final String ACCOUNTS_EDIT_FORM = "profile/accounts_edit";
    private static final String BILL_EDIT_FORM = "bills/bills_edit";
    private static final String BILL_ADD_FORM = "bills/bills_add";
    private static final String PROFILE_EDIT_FORM = "profile/profile_edit";
    private static final String PROFILE_USER_FORM = "profile/profile_user";
    private static final String PROFILE_FORM_REDIRECT_URL = "redirect:/profile";


    // Необходимые сервисы
    private final AccountService accountService;
    private final BillService billService;
    private final MeterService meterService;
    private final NewsService newsService;
    private final TariffService tariffService;
    private final UserService userService;

    // TODO Методы главного контроллера

    *//**
     * Перехват запроса главной страницы
     *//*
    @GetMapping("")
    public String getHomePage(@RequestParam(required = false) String error, Model model, Principal principal) {
        addUsername(model, principal);
        model.addAttribute("lastNews", newsService.getRelevantNews());
        return "index";
    }

    *//**
     * Перехват запроса регистрации нового пользователя
     *//*
    @GetMapping("/register")
    public String getRegisterPage(Model model, Principal principal) {
        addUsername(model, principal);
        model.addAttribute("userData", new UserDto());
        return REGISTER_FORM;
    }

    *//**
     * Перехват запроса создания нового пользователя
     *//*
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userData") UserDto userData,
                               BindingResult bindingResult,
                               Model model) {
        if (userService.createUser(userData) != null) {
            model.addAttribute("message",
                    String.format(USER_CREATED, userData.getUsername()));
        } else {
            bindingResult.rejectValue("username", "",
                    String.format(USER_HAS_ALREADY_CREATED, userData.getUsername()));
        }
        return REGISTER_FORM;
    }

    // TODO Методы контроллера счетчиков

    @GetMapping("/meters")
    public String getMetersPage(Model model, Principal principal) {
        if (principal != null) {
            UserDto user = userService.getUserByUsername(principal.getName());
            model.addAttribute("username", user.getPhone());
            model.addAttribute("user", user);
        }
        model.addAttribute("meterData", new MeterData());
        return "meters/meters_user";
    }

    @GetMapping("/meters/{id}")
    public String getMetersArchivePage(@PathVariable String id, Model model) {
        Meter meter = meterService.getMeter(Long.valueOf(id));
        model.addAttribute("meter", meter);
        model.addAttribute("account", meter.getAccount());

        model.addAttribute("meterDatas", meterService.getAllMeterDataByMeter(meter));
        return "meters/meters_archive";
    }

    @PostMapping("/submit")
    public String submitData(MeterData meterData) {
        if (meterData.getValue() != null && meterData.getValue() != 0) {
            meterService.submitMeterData(meterData);
        }
        return "redirect:/meters/";
    }

    @GetMapping("/meters/add")
    public String getAddPage(Model model, Principal principal) {
        model.addAttribute("accounts", accountService.getAccountsByUserUsername(principal.getName()));
        model.addAttribute("tariffs", tariffService.getAllTariffs());
        return "meters/meters_add";
    }

    @PostMapping("/meters/add")
    public String addMeter(Meter meter) {
        meterService.save(meter);
        return "redirect:/meters";
    }

    // TODO Методы Контроллер лицевых счетов

    *//**
     * Перехват запроса списка лицевых счетов
     *//*
    @GetMapping("/accounts")
    public String getAccountsPage(Model model, Principal principal) {
        addUsername(model, principal);
        model.addAttribute("accounts", accountService.getAllAccounts());
        return ACCOUNTS_ADMIN_FORM;
    }

    *//**
     * Перехват запроса списка лицевых счетов
     *//*
    @GetMapping("/accounts/edit")
    public String getAccountsEditPage(Model model, Principal principal) {
        addUsername(model, principal);
        model.addAttribute("accounts", accountService.getAllAccounts());
        return ACCOUNTS_EDIT_FORM;
    }

    // TODO Методы Контроллер обращений

    *//**
     * Перехват запроса главной страницы обращений
     *//*
    @GetMapping("/appeals")
    public String getAppealsPage(Model model, Principal principal) {
        addUsername(model, principal);
        return "appeals/appeals_user";
    }

    // TODO Методы Контроллер платежей

    @GetMapping("/bills")
    public String getBillsPage(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            model.addAttribute("username", username);
            model.addAttribute("accounts", accountService.getAccountsByUserUsername(username));
        }
        return "bills/bills_user";
    }

    @GetMapping("/bills/{billId}")
    public String showBill(@PathVariable Long billId, Model model) {
        model.addAttribute("bill", billService.getBillById(billId));
        return "/bills/bills_details";
    }

    @GetMapping("/bills/add")
    public String showCreationForm(Model model) {
        model.addAttribute("bill", new Bill());
        return BILL_ADD_FORM;
    }

    @PostMapping("/bills/add")
    public String processCreationForm(BillDto bill) {
        BillDto savedBill = billService.saveBill(bill);
        return "redirect:/bills/"; // + savedBill.getId();
    }

    @GetMapping("/bills/edit/{billId}")
    public String showUpdateBillForm(@PathVariable Long billId, Model model) {
        model.addAttribute(billService.getBillById(billId));
        return BILL_EDIT_FORM;
    }

    @PostMapping("/bills/edit/{billId}")
    public String processUpdateBillForm(BillDto bill, @PathVariable Long billId) {
        *//*bill.setId(billId);
        Bill savedBill = billService.saveBill(bill);*//*
        return "redirect:/bills/"; //+ savedBill.getId();
    }

    // TODO Методы Контроллер информации

    *//**
     * Перехват запроса страницы информации о компании
     *//*
    @GetMapping("/information/about")
    public String getAboutPage(Model model, Principal principal) {
        addUsername(model, principal);
        return "information/about";
    }

    *//**
     * Перехват запроса страницы контакты
     *//*
    @GetMapping("/information/contacts")
    public String getContactsPage(Model model, Principal principal) {
        addUsername(model, principal);
        return "information/contacts";
    }

    *//**
     * Перехват запроса страницы документы
     *//*
    @GetMapping("/information/documents")
    public String getDocumentsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "information/documents";
    }

    *//**
     * Перехват запроса страницы реквизитов
     *//*
    @GetMapping("/information/requisites")
    public String getRequisitesPage(Model model, Principal principal) {
        addUsername(model, principal);
        return "information/requisites";
    }

    // TODO Методы Контроллер модуля управления сайтом

    *//**
     * Перехват запроса главной страницы управления сайтом
     *//*
    @GetMapping("/management")
    public String getManagementPage(Model model, Principal principal) {
        addUsername(model, principal);
        return "management/management";
    }

    *//**
     * Перехват запроса списка всех пользователей
     *//*
    @GetMapping("/management/users")
    public String getAllUsersPage(Model model, Principal principal) {
        addUsername(model, principal);
        model.addAttribute("users", userService.getAllUsers());
        return "management/users";
    }

    *//**
     * Перехват запроса удаления пользователя
     *//*
    @DeleteMapping("/management/users/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/management/users";
    }

    // TODO Методы Контроллер новостей

    *//**
     * Перехват запроса архива новостей
     *//*
    @GetMapping("/news")
    public String getNewsArchivePage(Model model, Principal principal) {
        addUsername(model, principal);
        model.addAttribute("news", newsService.getAllNews());
        return "news/news_archive";
    }

    *//**
     * Перехват запроса страницы редактирования новостей
     *//*
    @GetMapping("/news/edit")
    public String getNewsEditPage(Model model, Principal principal) {
        addUsername(model, principal);
        model.addAttribute("news", newsService.getAllNews());
        return "news/news_edit";
    }

    *//**
     * Перехват запроса страницы редактирования новостей
     *//*
    @GetMapping("/news/single")
    public String getNewsSinglePage(Model model, Principal principal) {
        addUsername(model, principal);
        model.addAttribute("news", newsService.getAllNews());
        return "news/news_single";
    }

    // TODO Методы Контроллер фотогалереи

    *//**
     * Перехват запроса списка всех фотографий
     *//*
    @GetMapping("/photos")
    public String getPhotosPage(Model model, Principal principal) {
        addUsername(model, principal);
        return "photos/photos";
    }

    // TODO Методы Контроллер модуля профиля пользователя

    *//**
     * Перехват запроса на чтение профиля пользователя
     *//*
    @GetMapping("/profile")
    public String getProfilePage(Model model, Principal principal) {
        UserDto user = null;
        if (principal != null) {
            model.addAttribute("username", principal.getName());
            user = userService.getUserByUsername(principal.getName());
        }
        model.addAttribute("user", user);
        return PROFILE_USER_FORM;
    }

    *//**
     * Перехват запроса на изменение профиля пользователя
     *//*
    @PostMapping("/profile/edit")
    public String changeUserProfile(@Valid @ModelAttribute("userData") UserDto userData,
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
        return PROFILE_FORM_REDIRECT_URL;
    }

    *//**
     * Перехват запроса на изменение ФИО пользователя
     *//*
    @PostMapping("/profile/edit/name")
    public String editUserName(@Valid @ModelAttribute("user") UserDto userDto,
                               Principal principal) {
        if (userDto.getLastName() != null || userDto.getFirstName() != null || userDto.getPatronymic() != null) {
            userService.updateUser(userDto, principal.getName());
        }
        return PROFILE_FORM_REDIRECT_URL;
    }

    // TODO Методы Контроллер голосований

    *//**
     * Перехват запроса главной страницы голосований
     *//*
    @GetMapping("/votes")
    public String getVotesPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "votes/votes_user";
    }

    *//**
     * Временный метод для совместимости
     *//*
    private void addUsername(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
    }
    */
}
