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

<h2>Decode/Encode Secure Message/Payload</h2>
<h3>Encode</h3>

```java
SecureMessageEncoder.encode(Account senderPrivateKey, Account recipientPublicKey, String message) 
//or 
SecureMessageEncoder.encode(String senderPrivateKey, String recipientPublicKey, String message) 
```
<h3>Decode</h3>

```java
SecureMessageEncoder.decode(Account senderPrivateKey, Account recipientPublicKey, String encryptedPayload) 
//or 
SecureMessageEncoder.decode(String senderPrivateKey, String recipientPublicKey, String encryptedPayload) 
```

<h2>Fee Calculation</h2>

There are 2 ways to put a Fee. One can either just indicate a Fee using the Amount object or create Fee by creating a Custom Fee Calculation.

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
List<MosaicDefinitionMetaDataPair> AccountApi.getAccountOwnedMosaic(String address)
```

<h2>Nodes</h2>
<h3>Check Node Info</h3>

```java
NodeApi.getNodeInfo()
```

<h3>Check Node Extenteded Info</h3>

```java
NodeApi.getNodeExtendedInfo()
```


<h3>Check Nem Node Heartbeat</h3>

```java
NodeApi.getNemNodeHeartBeat()
```

<h2>Generate QR Code</h2>

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
