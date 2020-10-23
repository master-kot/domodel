package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.BillService;
import ru.geekbrains.domodel.services.api.RequisitesService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер платежей
 */
@ApiOperation(value = "Контроллер платежей (счетов)")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/bills")
@RequiredArgsConstructor
public class BillController {

    // Тип данных
    private final String DATA_TYPE = "application/json";

    // Необходимые сервисы
    private final BillService billService;
    private final AccountService accountService;
    private final RequisitesService requisitesService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ApiOperation(value = "Выдает список неоплаченых платежей по пользователю")
    @GetMapping(value = "", produces = DATA_TYPE)
    public ResponseEntity<Map<String, Object>> readUnpaidBillsByUser(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        List<AccountDto> accountDtos = accountService.getAllDtoByUserUsername(authentication.getName());
        response.put("accounts", accountDtos);
        response.put("bills", billService.getAllUnpaidDtoByAccounts(accountDtos));
        response.put("requisites", requisitesService.getCurrentDto());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Выдает архив платежей по пользователю")
    @GetMapping(value = "/archive", produces = DATA_TYPE)
    public ResponseEntity<Map<String, Object>> readArchiveBillsByUser(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        List<AccountDto> accountDtos = accountService.getAllDtoByUserUsername(authentication.getName());
        response.put("accounts", accountDtos);
        response.put("bills", billService.getAllDtoByAccounts(accountDtos));
        response.put("requisites", requisitesService.getCurrentDto());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @ApiOperation(value = "Выдает список всех платежей")
    @GetMapping(value = "/all", produces = DATA_TYPE)
    public ResponseEntity<Map<String, Object>> readAllBills(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        response.put("accounts", accountService.getAllDto());
        response.put("bills", billService.getAllDto());
        response.put("requisites", requisitesService.getCurrentDto());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    @ApiOperation(value = "Создает новость")
    @PostMapping
    @GetMapping("")
    public String getBillsPage(Authentication authentication) {
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
        //*bill.setId(billId);
        Bill savedBill = billService.saveBill(bill);
        return "redirect:/bills/"; //+ savedBill.getId();
    }

    @ApiOperation(value = "Создает новость")
    @PostMapping(consumes = CONSUME_TYPE)
    public ResponseEntity<BillDto> createNews(@RequestBody NewsRequest newsRequest,
                                              Authentication authentication) {
        return getBillDtoResponseEntity(billService.save(newsRequest, authentication));
    }

    @ApiOperation(value = "Выдает новость по ее номеру")
    @GetMapping("/{id}")
    public ResponseEntity<BillDto> readNewsById(@PathVariable Long id,
                                                Authentication authentication) {
        return getBillDtoResponseEntity(billService.getDtoById(id, authentication));
    }

    @ApiOperation(value = "Изменяет новость по ее номеру")
    @PostMapping("/{id}")
    public ResponseEntity<BillDto> updateNewsById(@PathVariable Long id,
                                                  @RequestBody BillDto BillDto,
                                                  Authentication authentication) {

        return getBillDtoResponseEntity(billService.updateById(id, BillDto, authentication));
    }

    @ApiOperation(value = "Изменяет видимость новости (условное удаление) по ее номеру")
    @PostMapping ("/{id}/visible")
    public ResponseEntity<Boolean> updateVisibilityNewsById(@PathVariable Long id,
                                                            @RequestBody boolean visible,
                                                            Authentication authentication){
        return new ResponseEntity<>(billService.updateVisibilityById(id, visible, authentication), HttpStatus.OK);
    }

    @ApiOperation(value = "Изменяет параметр закрепления новости по ее номеру")
    @PostMapping ("/{id}/pinned")
    public ResponseEntity<Boolean> updatePinningNewsById(@PathVariable Long id,
                                                         @RequestBody boolean pinned,
                                                         Authentication authentication){
        return new ResponseEntity<>(billService.updatePinningById(id, pinned, authentication), HttpStatus.OK);
    }

    // Формирует необходимый ответ в зависимости от содержания списка BillDtoList
    private ResponseEntity<List<BillDto>> getListResponseEntity(List<BillDto> BillDtoList) {
        return BillDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(BillDtoList, HttpStatus.OK);
    }

    // Формирует необходимый ответ в зависимости от содержания BillDto
    private ResponseEntity<BillDto> getBillDtoResponseEntity(BillDto BillDto) {
        return billDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(BillDto, HttpStatus.OK);
    }*/
}
