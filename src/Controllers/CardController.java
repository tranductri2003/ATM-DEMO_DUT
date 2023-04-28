package Controllers;

import Models.Card;
import Models.CardModel;

import java.sql.SQLException;

public class CardController {
    public Card getCard (String CardNumber, String Password) throws ClassNotFoundException, SQLException {
        CardModel model = new CardModel();
        Card card = model.getCard(CardNumber,Password);
        return card;
    }

    public void changePassword(Card card) throws SQLException, ClassNotFoundException {
        CardModel model = new CardModel();
        model.changePassword(card);
    }

    public void withdraw(Card card) throws ClassNotFoundException, SQLException {
        CardModel model = new CardModel();
        model.withdraw(card);
    }

    public void changeAmount(Card card) throws ClassNotFoundException, SQLException {
        CardModel model = new CardModel();
        model.deposit(card);
    }
}
