package ScroogeCoin;
import java.util.HashSet;
import java.util.Set;

public class TxHandler {

    // TODO current UTXOPool
    private UTXOPool utxoPool;

    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
    public TxHandler(UTXOPool utxoPool) {
        this.utxoPool = new UTXOPool(utxoPool);
    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool, 
     * (2) the signatures on each input of {@code tx} are valid, 
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     *     values; and false otherwise.
     */
    public boolean isValidTx(Transaction tx) {
        UTXOPool uniqueUTXOs = new UTXOPool();
        double previousTxOutSum = 0;
        double currentTxOutSum = 0;

        // TODO iterate over all inputs of transaction tx
        for (int i = 0; i < tx.numInputs(); i++) {
            Transaction.Input in = tx.getInput(i);
            UTXO utxo = new UTXO(in.prevTxHash, in.outputIndex);

            // TODO previous output of input transaction
            Transaction.Output output = utxoPool.getTxOutput(utxo);

            // TODO check if the transaction is in current UTXOPool
            if (!Crypto.verifySignature(output.address, tx.getRawDataToSign(i), in.signature))
                return false;

            // TODO check if the transaction is claimed only once
            if (uniqueUTXOs.contains(utxo))
                return false;

            // TODO add transaction to uniqueUTXOs
            uniqueUTXOs.addUTXO(utxo, output);

            // TODO add previous output value i.e. input value
            previousTxOutSum += output.value;
        }

        // TODO iterate over all transactions output
        for (Transaction.Output out : tx.getOutputs()) {
            // TODO check if the output value are non-negative
            if (out.value < 0)
                return false;

            // TODO add current output value
            currentTxOutSum += out.value;
        }

        // TODO check if the sum of the input value is less than sum of output value
        return previousTxOutSum >= currentTxOutSum;
    }

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {

        // TODO set to store all the valid transactions
        Set<Transaction> validTxs = new HashSet<Transaction>();
        // TODO iterate over all possible transactions
        for (Transaction tx : possibleTxs) {
            // TODO check if the transactions are valid
            if (isValidTx(tx)) {
                // TODO add transaction to set of valid transaction
                validTxs.add(tx);

                // TODO iterate over inputs of transactions to remove them from xurrent UTXOPool
                for (Transaction.Input in : tx.getInputs()) {
                    UTXO utxo = new UTXO(in.prevTxHash, in.outputIndex);
                    utxoPool.removeUTXO(utxo);
                }

                // TODO iterate over outputs of transactions to add them to current UTXOPool
                for (int i = 0; i < tx.numOutputs(); i++) {
                    Transaction.Output out = tx.getOutput(i);
                    UTXO utxo = new UTXO(tx.getHash(), i);
                    utxoPool.addUTXO(utxo, out);
                }
            }
        }

        // TODO convert set of transactions to array of transactions
        Transaction[] validTxArray = new Transaction[validTxs.size()];
        return validTxs.toArray(validTxArray);
    }
}
