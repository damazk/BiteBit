**Bitcoin Wallet (Signet, P2WPKH) using bitcoinj**

This project is a lightweight Bitcoin wallet built with the [**bitcoinj**](https://bitcoinj.github.io/) library. It supports the **Signet** test network and uses the **P2WPKH** (Pay-to-Witness-Public-Key-Hash) address format for SegWit compatibility.

### Key Features:

* 🔐 **Wallet Generation**: Generates a new HD wallet using BIP39 mnemonic phrases and BIP32 key derivation.
* 🪙 **Address Management**: Supports native SegWit addresses (Bech32 / P2WPKH).
* 🌐 **Signet Network**: Interacts with Bitcoin’s Signet test network — useful for development without real funds.
* 📥 **UTXO Handling**: Retrieves unspent transaction outputs from external APIs and constructs custom transactions.
* ✍️ **Transaction Signing**: Signs transactions locally using private keys derived from the HD wallet.
* 📤 **Broadcasting**: Serialized transactions can be broadcast through a Bitcoin node or third-party service.

### Technologies:

* Kotlin
* bitcoinj (custom fork or recent version with Signet and SegWit support)
* JSON APIs (for UTXO retrieval and broadcasting)
* Base58, Bech32 encoding/decoding

This project demonstrates a clear understanding of Bitcoin internals, including transaction structure, key management, and SegWit scripting.
