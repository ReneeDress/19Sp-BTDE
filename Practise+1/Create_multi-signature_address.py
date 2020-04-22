#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""

Title - Create multi-signature address

Thisprogram demonstrates the creation of
Multi-signature bitcoin address.

Created on Wed Apr  8 11:05:32 2020

@author: reneelin

"""

#import bitcoin
from bitcoin import *

#Create Private Keys
my_private_key1 = random_key()
my_private_key2 = random_key()
my_private_key3 = random_key()

print ("Private Key1: %s\n" % my_private_key1)
print ("Private Key2: %s\n" % my_private_key2)
print ("Private Key3: %s\n" % my_private_key3)
print ('\n')

#Create Public Keys
my_public_key1 = privtopub(my_private_key1)
my_public_key2 = privtopub(my_private_key2)
my_public_key3 = privtopub(my_private_key3)
print ("Public Key1: %s\n" % my_public_key1)
print ("Public Key2: %s\n" % my_public_key2)
print ("Public Key3: %s\n" % my_public_key3)
print ('\n')

#Create Multi-signature bitcoin address
my_multi_sig = mk_multisig_script(my_private_key1, my_private_key2, my_private_key3, 2,3)
my_multi_address = scriptaddr(my_multi_sig)
print ("Multi signature address: %s" % my_multi_address)