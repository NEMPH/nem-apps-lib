# NEM Apps Library

Java API library for NEM.io blockchain platform. This directly calls the https://github.com/NEMModules/nem.core that then calls the endpoints based on https://bob.nem.ninja/docs/

Library has the following features/functionalities

  * Initiate a transfer transactoin (including mosaic)
  * Initiate a multisig transaction (including mosaic)
  * Cosign a multisig transaction
  * Configurable Custom Fee Calculation 
  * Get All Transaction (Confirmed/Unconfirmed/Incoming/Outgoing) for an Account
  * Get All Owned Mosaics of an Account
  * Node Information and Check Node Heartbeats
  * Extra: Code to Generate QR Code.

<h2>Technology Stack</h2>

Java 1.8 | nem.core

<h2>How to build</h2>
Make sure to clone NEMModules fork of nem.core and build.

```bash
git clone https://github.com/NEMModules/nem.core
cd nem.core
mvn clean install
```

build the nem-apps-lib after.

```bash
git clone https://github.com/NEMModules/nem-apps-lib.git
cd nem-apps-lib
mvn clean install
```

Import it as a maven dependency

```xml
<dependency>
    <groupId>io.nem.apps</groupId>
    <artifactId>nem-apps-lib</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

<h2>Configuration Setup</h2>

Before starting, make sure you have the configuration setup.

```java
ConfigurationBuilder.nodeNetworkName("mijinnet")
    .nodeNetworkProtocol("http")
    .nodeNetworkUri("a1.nem.foundation")
    .nodeNetworkPort("7895")
    .setup();
```

<h2>Transaction Callbacks</h2>

Developers can catch callbacks before and after a transaction is made. All the developer needs to do is define a Callback class and use it either on per Transaction or for All Transaction.

TBD

<h2>Transactions</h2>

Use the following set of builders to create transactions.
          
<h3>Transfer Transaction</h3>

```java
TransactionBuilder.initiateTransactionBuild()
    .sender(new Account(this.senderPrivateKeyPair))
    .recipient(new Account(this.recipientPublicKeyPair))
    .amount(0l)
    .buildAndSendTransaction();
```  

<h3>Multisig Transaction</h3>

```java

TransferTransaction transaction = TransactionBuilder.initiateTransactionBuild()
    .sender(new Account(this.senderPrivateKeyPair)) // multisig account
    .recipient(new Account(this.recipientPublicKeyPair)) 
    .amount(0l)
    .buildAndSendTransaction();
    
TransactionBuilder.initiateMultisigTransactionBuild()
	.sender(this.senderPrivateAccount) // co-signer as sender
	.otherTransaction(transaction)
	.buildAndSendMultisigTransaction();
 ```  
  
<h3>MultisigSignature Transaction</h3>

```java
TransactionBuilder.initiateMultisigSignatureTransactionBuild()
    .sender(this.senderPrivateAccount) // signer
    .multisig(this.multisigPublicAccount) // multisig account
    .otherTransaction(Hash.fromHexString("hash")) // hash
    .cosign();
 ```  
 
<h2>Decode/Encode Secure Message/Payload</h2>

Use the following static methods to encode and decode Secure Message Payloads.

<h3>Encode</h3>

```java
SecureMessageEncoder.encode(Account senderPrivateKey, Account recipientPublicKey, String message) 
//or 
SecureMessageEncoder.encode(String senderPrivateKey, String recipientPublicKey, String message) 
```
<h3>Decode</h3>

```java
SecureMessageDecoder.decode(Account senderPrivateKey, Account recipientPublicKey, String encryptedPayload) 
//or 
SecureMessageDecoder.decode(String senderPrivateKey, String recipientPublicKey, String encryptedPayload) 
```

<h2>Fee Calculation</h2>

There are 2 ways to put a Fee. One can either just indicate a Fee using the Amount object or create Fee by creating a Custom Fee Calculation.

<h3>Fee on the Transaction</h3>

```java
TransactionBuilder.initiateTransactionBuild()
    .sender(new Account(this.senderPrivateKeyPair))
    .recipient(new Account(this.recipientPublicKeyPair))
    .amount(0l)
    .fee(Amount.fromMicroNem(0l)) // fee
    .buildAndSendTransaction();
``` 

<h3>Fee Calculation via Fee Calculation Object</h3>

```java
TransactionBuilder.initiateTransactionBuild()
    .sender(new Account(this.senderPrivateKeyPair))
    .recipient(new Account(this.recipientPublicKeyPair))
    .amount(0l)
    .feeCalculator(new TransactionFeeCalculatorAfterForkForApp()) // custom fee calculator
    .buildAndSendTransaction();
``` 

<h3>Global and Transaction Level Fees</h3>

Fees can also be configurable. With the API, the developers can put in their own Fee Calculation on either per Transaction or for All Transaction.

<h2>Accounts</h2>
<h3>Generate a new Account</h3>

```java
KeyPairViewModel AccountApi.generateAccount()
```

<h3>Get Account Info using Address</h3>

```java
AccountMetaDataPair AccountApi.getAccountByAddress(String address) 
```

<h3>Get All Transactions for an Account</h3>

```java
List<TransactionMetaDataPair> AccountApi.getAllTransactions(String address)
```

<h3>Get All Confirmed Transactions for an Account</h3>

```java
List<TransactionMetaDataPair> AccountApi.getAllConfirmedTransactions(String address)
```

<h3>Get All Unconfirmed Transactions for an Account</h3>

```java
List<TransactionMetaDataPair> AccountApi.getAllUnconfirmedTransactions(String address)
```

<h3>Get All Incoming Transactions for an Account</h3>

```java
List<TransactionMetaDataPair> AccountApi.getIncomingTransactions(String address)
```

<h3>Get All Outgoing Transactions for an Account</h3>

```java
List<TransactionMetaDataPair> AccountApi.getOutgoingTransactions(String address)
```

<h3>Get All Mosaics for an Account</h3>

```java
List<Mosaic> AccountApi.getAccountOwnedMosaic(String address)
```

<h2>Nodes</h2>
<h3>Check Node Info</h3>

```java
Node NodeApi.getNodeInfo()
```

<h3>Check Node Extenteded Info</h3>

```java
NisNodeInfo NodeApi.getNodeExtendedInfo()
```


<h3>Check Nem Node Heartbeat</h3>

```java
NemRequestResult NodeApi.getNemNodeHeartBeat()
```

<h2>Extra: Generate QR Code</h2>

```java
String qrCodeText = "<your key>";
String filePath = "<where to put the qr image>";
int size = 125;
String fileType = "png";
File qrFile = new File(filePath);
try {
    QRCodeUtils.createQRImage(qrFile, qrCodeText, size, fileType);
} catch (WriterException | IOException e) {
    e.printStackTrace();
}
```

<sub>Copyright (c) 2017</sub>
