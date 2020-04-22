# import blockchain library
from blockchain import blockexplorer
# get a particular block
block = blockexplorer.get_block('0000000000000000000a83ed02c1a99ef00bed35b9be8362267c442fd7328621')
print("Block Fee: %s\n" % block.fee)
print("Block Size: %s\n" % block.size)
print("Block Transactions: %s\n" % block.transactions)
# get the latest block
block = blockexplorer.get_latest_block()
lbhash = block.hash
block = blockexplorer.get_block(lbhash)
print("Latest Block Fee: %s\n" % block.fee)
print("Latest Block Size: %s\n" % block.size)
print("Latest Block Transactions: %s\n" % block.transactions)