#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Title - Bitcoin Transaction History

This program demonstrates listing history of a bitcoin address.

Created on Wed Apr  8 11:32:00 2020

@author: reneelin

"""

#import bitcoin
from bitcoin import *

#View address transaction history
a_valid_bitcoin_address = '12dRugNcdxK39288NjcDV4GX7rMsKCGn6B'
print (history(a_valid_bitcoin_address))