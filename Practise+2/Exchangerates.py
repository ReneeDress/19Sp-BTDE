#import blockchain library
from blockchain import exchangerates
# get the Bitcoin rates in various currenices
ticker = exchangerates.get_ticker()
# print the Bitcoin price for every currency
print("15 minutes delayed market Bitcoin Prices in various currencies:")
for k in ticker:
    print(k, ticker[k].p15min)
print("the most recent market Bitcoin Latest Prices in various currencies:")
for k in ticker:
    print(k, ticker[k].last)
# getting Bitcoin value for a particular amount and currency
btc = exchangerates.to_btc('EUR', 100)
print("\n100 euros in Bitcoin: %s" % btc)
btc = exchangerates.to_btc('CNY', 100)
print("\n100 CNY in Bitcoin: %s" % btc)