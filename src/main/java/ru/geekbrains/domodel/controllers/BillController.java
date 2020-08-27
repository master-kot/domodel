package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.entities.Bill;
import ru.geekbrains.domodel.services.api.BillService;

import java.security.Principal;

/**
 * Контроллер платежей
 */
@Controller
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    // Сервис счетов
    private final BillService billService;

    private static final String BILL_EDIT_FORM = "bills/bills_edit";
    private static final String BILL_ADD_FORM = "bills/bills_add";

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("")
    public String getBillsPage(Model model, Principal principal, Authentication authentication) {
        assert principal != null;
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        // todo сделать нормально на случай если у админа больше одной роли
        if ("admin".equals(authentication.getName())) {
            model.addAttribute("bills", billService.getAllBills());
        } else {
            model.addAttribute("bills", billService.findAllByUsername(principal.getName()));
        }
        return "bills/bills_user";
    }

    @GetMapping("/{billId}")
    public String showBill(@PathVariable Long billId, Model model) {
        model.addAttribute("bill", billService.findById(billId));
        return "/bills/bills_details";
    }

    @GetMapping("/add")
    public String showCreationForm(Model model) {
        model.addAttribute("bill", new Bill());
        return BILL_ADD_FORM;
    }

    @PostMapping("/add")
    public String processCreationForm(Bill bill) {
        Bill savedBill = billService.save(bill);
        return "redirect:/bills/" + savedBill.getId();
    }

    @GetMapping("/edit/{billId}")
    public String showUpdateBillForm(@PathVariable Long billId, Model model) {
        model.addAttribute(billService.findById(billId));
        return BILL_EDIT_FORM;
    }

    @PostMapping("/edit/{billId}")
    public String processUpdateBillForm(Bill bill, @PathVariable Long billId) {
        bill.setId(billId);
        Bill savedBill = billService.save(bill);
        return "redirect:/bills/" + savedBill.getId();
    }
}
