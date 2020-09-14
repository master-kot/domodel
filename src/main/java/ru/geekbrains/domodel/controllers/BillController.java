package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.BillService;

/**
 * Контроллер платежей
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/bills")
@RequiredArgsConstructor
public class BillController {

    // Необходимые сервисы
    private final BillService billService;
    private final AccountService accountService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /*
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
    */
}
