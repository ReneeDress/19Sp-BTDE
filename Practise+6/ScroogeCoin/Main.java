package ScroogeCoin;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main (String[] args) {
        // TODO Auto-generated method stub
        Transaction tx = new Transaction();
        UTXOPool uxpool = new UTXOPool();
        TxHandler txhandler = new TxHandler(uxpool);
        System.out.println(txhandler.isValidTx(tx));
        Set<Transaction> validTxs = new HashSet<Transaction>();
        Transaction[] validTxArray = new Transaction[validTxs.size()];
        System.out.println(txhandler.handleTxs(validTxs.toArray(validTxArray)));
        // TODO Auto-generated method stub MaxFeeTxHandler
        Transaction tx2 = new Transaction();
        MaxFeeTxHandler maxFeeTxHandler = new MaxFeeTxHandler(uxpool);
        System.out.println(maxFeeTxHandler.isValidTx(tx));
        Set<Transaction> acceptedTx = new HashSet<Transaction>();
        Transaction[] maxValidTxArray = new Transaction[acceptedTx.size()];
        System.out.println(maxFeeTxHandler.handleTxs(acceptedTx.toArray(maxValidTxArray)));
    }
}