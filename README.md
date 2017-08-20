# NEM/SpectroCoin Java API Library

<h2>Configuration Setup</h2>

Before starting, make sure you have the configuration setup.

```java
  ConfigurationBuilder.nodeNetworkName("mijinnet")
      .nodeNetworkProtocol("http")
      .nodeNetworkUri("a1.nem.foundation")
      .nodeNetworkPort("7895")
      .setup();
```

<h2>Transactions</h2>



        
          
<h3>Generic Transaction</h3>

```java
TransactionBuilder.initiateTransactionBuild()
    .sender(new Account(this.senderPrivateKeyPair))
    .recipient(new Account(this.recipientPublicKeyPair))
    .amount(0l)
    .buildAndSendTransaction();
```  


<h3>MultiSig Transaction</h3>

```java
  TransactionBuilder.initiateMultisigTransactionBuild()
    .sender(this.senderPrivateAccount)
    .recipient(this.recipientPublicAccount)
    .amount(0l)
    .multisig(this.multiSigAccount)
    .buildAndSendMultisigTransaction();
  ```  
  
<h3>MultiSigSignature Transaction</h3>

```java
  TransactionBuilder.initiateMultisigSignatureTransactionBuild()
    .sender(this.senderPrivateAccount)
    .recipient(this.recipientPublicAccount)
    .amount(0l)
    .multisig(this.multiSigAccount)
    .buildAndSendMultisigTransaction();
  ```  

<h2>Fee Calculation</h2>

There are 2 ways to put a Fee. One can either just indicate a Fee using the Amount object or create Fee by creating a Custom Fee Calculation.

<h2>Accounts</h2>
<h3>Generate a new Account</h3>


<h3>Get Account Info using Address</h3>
<h3>Get All Transactions for an Account</h3>
<h3>Get All Confirmed Transactions for an Account</h3>
<h3>Get All Unconfirmed Transactions for an Account</h3>
<h3>Get All Incoming Transactions for an Account</h3>
<h3>Get All Outgoing Transactions for an Account</h3>
<h3>Get All Mosaics for an Account</h3>

<h2>Nodes</h2>
<h3>Check Node Info</h3>
<h3>Check Node Extenteded Info</h3>
<h3>Check Node Heartbeat</h3>
<h2>Generate QR Code</h2>

<sub>Copyright (c) 2017</sub>
