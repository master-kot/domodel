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
 * Контроллер счетов (платежных документов)
 */
@Controller
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    // Сервис счетов
    private final BillService billService;

    private static final String BILL_CREATE_OR_UPDATE_FORM = "bills/bill-form";

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
        return "bills/bill-list";
    }

    @GetMapping("/{billId}")
    public String showBill(@PathVariable Long billId, Model model) {
        model.addAttribute("bill", billService.findById(billId));
        return "/bills/bill-details";
    }

    @GetMapping("/new")
    public String showCreationForm(Model model) {
        model.addAttribute("bill", new Bill());
        return BILL_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(Bill bill) {
        Bill savedBill = billService.save(bill);
        return "redirect:/bills/" + savedBill.getId();
    }

    @GetMapping("/{billId}/edit")
    public String showUpdateBillForm(@PathVariable Long billId, Model model) {
        model.addAttribute(billService.findById(billId));
        return BILL_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{billId}/edit")
    public String processUpdateBillForm(Bill bill, @PathVariable Long billId) {
        bill.setId(billId);
        Bill savedBill = billService.save(bill);
        return "redirect:/bills/" + savedBill.getId();
    }

}
