package Controllers;

import Models.Account;
import Models.AccountModel;
import Models.Card;
import Models.CardModel;

import java.sql.SQLException;

public class AccountController {
    public Account getAccount (Card card) throws ClassNotFoundException, SQLException {
        AccountModel model = new AccountModel();
        Account account = model.getAccount(card.getAccountID());
        return account;
    }
}
