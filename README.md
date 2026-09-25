# Saviynt Sample Connector

A template project for building custom connectors for [Saviynt Security Manager (SSM)](https://saviynt.com). Use this as a starting point to integrate any target system with SSM for identity lifecycle management.

## Overview

Custom connectors allow SSM to manage accounts, entitlements, and users in external systems. This project provides a skeleton implementation of `BaseConnectorSpecification` with stub methods for every supported operation.

## Prerequisites

- The [Saviynt Security Manager (SSM)](https://saviynt.com) should be on 25.Brisbane or above.
- Java 21
- Maven 3.x

## Project Structure

```
sampleConnector/
├── lib/
│   └── abstractConnector-9.4.9.jar   # Saviynt connector framework (local dependency)
├── src/main/java/com/external/sample/
│   └── SampleConnector.java           # Main connector implementation
└── pom.xml
```

## Build

```bash
mvn clean package
```

The output JAR is written to `target/sample-1.0.jar`.

## Implementation

`SampleConnector` extends `BaseConnectorSpecification`. Implement the methods below to connect SSM to your target system.

### Connection configuration

| Method | Purpose |
|--------|---------|
| `setConfig(ConfigDataVo)` | Declare connection attributes shown on the SSM connection UI (e.g. URL, Username, Password) |
| `encryptedConnectionAttributes(ConfigDataVo)` | Mark which attributes should be stored encrypted |
| `connectionAttributesDescription(ConfigDataVo)` | Provide UI hints/descriptions for each attribute |
| `test(configData, data)` | Verify connectivity; return `{"status": true/false}` |

### Reconciliation

| Method | Purpose |
|--------|---------|
| `reconcile(configData, data, formatterClass)` | Pull accounts, users, and entitlements from the target system and push them to SSM via `RepositoryReconService.notify()` |
| `getSummary(configData, data)` | Return object counts (e.g. `{"Account": 100}`) |
| `checkExisting(configData, data, searchableObject)` | Check whether a given record exists in SSM |

### Account lifecycle

| Method | Purpose |
|--------|---------|
| `createAccount` | Provision a new account |
| `updateAccount` | Modify an existing account |
| `enableAccount` | Enable a disabled account |
| `disableAccount` | Disable an active account |
| `lockAccount` | Lock an account |
| `unLockAccount` | Unlock an account |
| `removeAccount` | Delete/deprovision an account |
| `terminateAccount` | Terminate an account |

### Entitlement lifecycle

| Method | Purpose |
|--------|---------|
| `createEntitlement` | Create an entitlement in the target system |
| `updateEntitlement` | Update an entitlement |
| `addAccessToEntitlement` | Grant access to an entitlement |
| `removeEntitlement` | Remove an entitlement |
| `removeAccessToEntitlement` | Revoke access to an entitlement |

### Account access

| Method | Purpose |
|--------|---------|
| `addAccessToAccount` | Add entitlement access to an account |
| `removeAccessToAccount` | Remove entitlement access from an account |

### User lifecycle

| Method | Purpose |
|--------|---------|
| `createUser` | Create a user in the target system |
| `updateUser` | Update a user |
| `changePassword` | Change a user's password |
| `validateCredentials` | Validate credentials against the target system |

### Firefighter (privileged access)

| Method | Purpose |
|--------|---------|
| `firefighterIdGrantAccess` | Grant firefighter ID access (triggered by provisioning job) |
| `firefighterIdRevokeAccess` | Revoke firefighter ID access |
| `firefighterIdInstanceGrantAccess` | Grant firefighter instance access (triggered immediately on task creation) |
| `firefighterIdInstanceRevokeAccess` | Revoke firefighter instance access |
