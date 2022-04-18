package multi.fclass.iMint.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import multi.fclass.iMint.transaction.service.ITransactionService;
import multi.fclass.iMint.transaction.service.TransactionServiceImpl;

/**
 * @author GhostFairy
 *
 */
@RestController
public class TransactionController {

	@Autowired
	ITransactionService trxService = new TransactionServiceImpl();

}
