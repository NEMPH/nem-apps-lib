# NEM Apps Library

Java API library for NEM.io blockchain platform. This directly calls the https://github.com/NEMModules/nem.core that then calls the endpoints based on https://bob.nem.ninja/docs/

Library has the following features/functionalities

  * Initiate a transfer transactoin (including mosaic)
  * Initiate a multisig transaction (including mosaic)
  * Cosign a multisig transaction
  * Configurable Custom Fee Calculation 
  * Get All Transaction (Confirmed/Unconfirmed/Incoming/Outgoing) for an Account
  * Get All Owned Mosaics of an Account
  * Get Block and Chain Info
  * Node Information and Check Node Heartbeats
  * Transaction Monitoring (using nem-transaction-monitor)

<h2>Technology Stack</h2>

  * Java 1.8
  * nem.core

<h2>How to build</h2>
Make sure to clone NEMModules fork of nem.core and build.

```bash
git clone https://github.com/NEMPH/nem.core.git
cd nem.core
mvn clean install
```

build the nem-apps-lib after.

```bash
git clone https://github.com/NEMPH/nem-apps-lib.git
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

Before starting, make sure you have the node endpoint is all set. Note that this should only be called once.

```java
ConfigurationBuilder.nodeNetworkName("<network name>")
    .nodeNetworkProtocol("http")
    .nodeNetworkUri("<node url>")
    .nodeNetworkPort("7895")
    .setup();
    
```

You can also change the node endpoint on the fly.
```java
Globals.setNodeEndpoint(new NodeEndpoint("http","<node url>",7895));
```

<h2>Transactions</h2>

Use the following set of builders to create transactions.
          
<h3>Transfer Transaction</h3>

```java
TransferTransactionBuilder.sender(new Account(this.senderPrivateKeyPair))
    .recipient(new Account(this.recipientPublicKeyPair))
    .amount(0l)
    .buildAndSendTransaction();
```  

<h3>Multisig Transaction</h3>

```java

TransferTransaction transaction = TransferTransactionBuilder
    .sender(new Account(this.senderPrivateKeyPair)) // multisig account
    .recipient(new Account(this.recipientPublicKeyPair)) 
    .amount(0l)
    .buildTransaction();
    
MultisigTransactionBuilder.sender(this.senderPrivateAccount) // co-signer as sender
    .otherTransaction(transaction)
    .buildAndSendMultisigTransaction();
	
 ```  
  
<h3>MultisigSignature Transaction</h3>

<h4>Single Signer</h4>

```java
MultisigSignatureTransactionBuilder.multisig(this.multisigPublicAccount) // multisig account
    .signer(this.senderPrivateAccount) // signer
    .otherTransaction(Hash.fromHexString("hash")) // hash
    .buildMultisigSignatureTransaction();
```  
 
<h4>Multiple Signer</h4>

```java
MultisigSignatureTransactionBuilder.multisig(this.multisigPublicAccount) // multisig
		.startAssignSigners()
			.addSigner(this.senderPrivateAccount1) // signer 1
			.addSigner(this.senderPrivateAccount2) // signer 2
		.endAssignSigners()
	.otherTransaction(Hash.fromHexString("hash"))
	.coSign();
``` 
 
<h2>Transaction Callbacks</h2>

Developers can catch callbacks before and after a transaction is made. All the developer needs to do is define a Callback class and use it either on per Transaction or for All Transaction.

<h2>Fee Calculation</h2>

Fees can be calculated either on a Global level or per transaction. 

<h3>Global Level Fees</h3>

Fees can also be configurable. With the API, the developers can put in their own Fee Calculation on either per Transaction or for All Transaction.

```java
ConfigurationBuilder.nodeNetworkName("<network name>").nodeNetworkProtocol("http")
	.nodeNetworkUri("<node url>").nodeNetworkPort("7895")
	.transactionFee(new TransactionFeeCalculatorAfterFork()) // global
	.setup();
```

<h3>Transaction Level Fees</h3>

<h4>Fee on the Transaction</h4>

fee can also be set on the transaction level via the fee() method.

```java
TransferTransactionBuilder
    .sender(new Account(this.senderPrivateKeyPair))
    .recipient(new Account(this.recipientPublicKeyPair))
    .amount(0l)
    .fee(Amount.fromMicroNem(0l)) // fee
    .buildAndSendTransaction();
``` 

<h4>Fee Calculation via Fee Calculation Object</h4>

fee calculation can also be set on the transaction level using the feeCalculator() method.

```java
TransferTransactionBuilder
    .sender(new Account(this.senderPrivateKeyPair))
    .recipient(new Account(this.recipientPublicKeyPair))
    .amount(0l)
    .feeCalculator(new TransactionFeeCalculatorAfterForkForApp()) // custom fee calculator
    .buildAndSendTransaction();
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
SecureMessageDecoder.decode(Account recipientPrivateKey, Account senderPublicKey, String encryptedPayload) SecureMessageDecoder.decode(Account recipientPrivateKey, Account senderPublicKey, byte[] encryptedPayload) 
//or 
SecureMessageDecoder.decode(String recipientPrivateKey, String senderPublicKey, String encryptedPayload)
SecureMessageDecoder.decode(String recipientPrivateKey, String senderPublicKey, byte[] encryptedPayload) 
```

<h2>Data Encryption (Encrypt/Decrypt) from nem.core</h2>

```java

CryptoEngine engine = CryptoEngines.ed25519Engine();

//encrypt
byte[] encrypted = engine
		.createBlockCipher(
				new KeyPair(PrivateKey.fromHexString(xPvkey), engine),
				new KeyPair(PublicKey.fromHexString(xPubkey), engine))
		.encrypt("hello".getBytes());

// decrypt
byte[] decrypted = engine
		.createBlockCipher(
				new KeyPair(PublicKey.fromHexString(xPubkey), engine),
				new KeyPair(PrivateKey.fromHexString(xPvkey), engine)).decrypt(encrypted);
```

<h2>Accounts</h2>
<h3>Generate a new Account</h3>

```java
GeneratedAccount AccountApi.generateAccount()
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
<h2>Blocks</h2>

<h3>Get Block by Block Height</h3>

```java
Block BlockApi.getBlockByHeight(int blockHeight)
```

<h3>Get Block After Given Block Height</h3>

```java
Block getBlockAfterGivenBlockHeight(int height) 
```

<h2>Chains</h2>

<h3>Get Chain Height</h3>

```java
Block ChainApi.getChainHeight()
```

<h3>Get Chain Last Score</h3>

```java
Block ChainApi.getChainScore()
```

<h3>Get Chain Last Block</h3>

```java
Block ChainApi.getChainLastBlock()
```

<h2>Validations</h2>

<h3>Validate if Address is Valid</h3>

```java
boolean ValidationApi.isAddressValid(String address)
```

```java
boolean ValidationApi.isAddressValid(String address, NodeEndpoint nodeEndpoint)
```

```java
boolean ValidationApi.isAddressValid(String address, String protocol, String host, int port)
```

```java
boolean ValidationApi.isAddressPatternValid(String address)
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

<h2>Incorporating Transaction Monitor</h2>
You can incorporate the nem-transaction-monitor library to monitor the transactions that's coming in.

```bash
git clone https://github.com/NEMPH/nem-transaction-monitor.git
cd nem-transaction-monitor
mvn clean install
```
Import maven dependency
```xml
<dependency>
    <groupId>io.nem.apps</groupId>
    <artifactId>nem-transaction-monitor</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

```java

WsNemTransactionMonitor.init().host("<node url>").port("7895").wsPort("7778")
	.addressToMonitor("MDYSYWVWGC6JDD7BGE4JBZMUEM5KXDZ7J77U4X2Y") // address to monitor
	.subscribe(io.nem.utils.Constants.URL_WS_TRANSACTIONS, new TransactionMonitor()) // multiple subscription and a handler
	.subscribe(io.nem.utils.Constants.URL_WS_UNCONFIRMED, new UnconfirmedTransactionMonitor())
	.monitor(); // trigger the monitoring process
```

<h3>Custom Transaction Monitor</h3>
You can create your own handler to handle the incoming payload. 

```java
public class CustomTransactionMonitor implements TransactionMonitorHandler {
	@Override
	public Type getPayloadType(StompHeaders headers) {
		return String.class;
	}
	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		System.out.println(payload.toString()); // handle the payload.
	}
}
```

<h2>Support</h2>
Need help integration your Java Application with NEM.io Platform? I can definitely help you with that, send me a message via 

[telegram](https://web.telegram.org/#/im?p=@brambear)


Tips are appreciated but not required. :bowtie: :muscle: :metal:  
XEM: NA6IT2-ZSTQLT-YO223Z-ZMH2J7-2GVG7G-ZY72FN-47IF  
BTC: 3JYAYPxN9RL4UvbxMd1svbQynMpFbf5ehy 

<sub>Copyright (c) 2017</sub>
