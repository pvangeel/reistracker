package org.goldflower.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Controller
public class PaymentController {
    
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @RequestMapping(value = "total", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public double getTotalAmountPayed() {
        return paymentRepository.getTotalAmountPayed();
    }

    @RequestMapping(value = "payment", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

    @RequestMapping(value = "registerpayment", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void registerPayment(@RequestBody Payment payment) {
        paymentRepository.persist(payment);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("Error handling request", ex);
        return new ResponseEntity(INTERNAL_SERVER_ERROR);
    }
}
