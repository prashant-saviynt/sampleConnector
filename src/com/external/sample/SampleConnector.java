package com.external.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.saviynt.ssm.abstractConnector.BaseConnectorSpecification;
import com.saviynt.ssm.abstractConnector.ConfigDataVo;
import com.saviynt.ssm.abstractConnector.ExposedObject;
import com.saviynt.ssm.abstractConnector.RepositoryReconService;
import com.saviynt.ssm.abstractConnector.SaviyntReadOnlyObject;
import com.saviynt.ssm.abstractConnector.SearchableObject;
import com.saviynt.ssm.abstractConnector.exceptions.ConnectorException;
import com.saviynt.ssm.abstractConnector.exceptions.InvalidAttributeValueException;
import com.saviynt.ssm.abstractConnector.exceptions.InvalidCredentialException;
import com.saviynt.ssm.abstractConnector.exceptions.MissingKeyException;
import com.saviynt.ssm.abstractConnector.exceptions.OperationTimeoutException;
/**
* SampleConnector is an Example custom connector provided to explain how one can build
* their own connector to manage accounts, entitlements etc in any target application via SSM. You need to
* implement various identity lifecycle methods like createAccount(),reconcile() etc from  BaseConnectorSpecification class. 
* These methods will be invoked by SSM while provisioning, deprovisioning, reconciling user, account and access
* to/from the target system.
**/
public class SampleConnector extends BaseConnectorSpecification {

	 
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/**
		 * to retrieve connector display name.
		 *
		 * @return the string
		 */
		@Override
		public String displayName() {
		 
			return "DummyConnector";
		}

		/**
		 *  to retrieve connector version.
		 *
		 * @return the string
		 */
		@Override
		public String version() {
			 
			return "1.0";
		}
		/**
		 * to test the connection
		 * Example : To test the connection , refer to the below steps
		 * step 1  : retrieve connection attributes from configData/Data
		 * step 2  : connect to target system using JDBC connection
		 * step 3  : return true if connection is successful
		 *
	     * @param configData the configData This is a metadata that contains the details of the information required 
		          and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime.
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 * @throws InvalidCredentialException the invalid credential exception
		 * @throws InvalidAttributeValueException the invalid attribute value exception
		 * @throws OperationTimeoutException the operation timeout exception
		 * @throws MissingKeyException the missing key exception
		 */
		@Override
		public Boolean test(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException,
				InvalidCredentialException, InvalidAttributeValueException, OperationTimeoutException, MissingKeyException {
			//logic to test connection
			//connect to target system using configData
			//write your logic
			//return true or false 
			return true;
		}
		/**
		 * to set the config with attributes needed for creating a connection to the target system from SSM.
		 * The attributes defined in configData in setConfig are the attributes that would dynamically
		 * populate on the connection creation UI under SSM to be inputed.
		 * Connection attributes can be added in following way:
		 * Example: List<String> connectionAttributes = configData.getConnectionAttributes();
		 * connectionAttributes.add("drivername");
		 * 
		 * Connection attributes that need to be encrypted can be added to configData as below:
		 * Example : List<String> encryptedConnectionAttributes = configData.getEncryptedConnectionAttributes();
		 *			 encryptedConnectionAttributes.add("Password");
		 *
		 * Description or details of the format in which the config attributes are supposed to be inputted from
		 * the UI can be added in configData as below:
		 * JSONObject jsonObject = new JSONObject(connectionAttributesDescription);
		 * jsonObject.put("Password", "Provide password to connect with application");
		 * jsonObject.put("CreateUserJSON", "SAMPLE JSON {}");
		 * configData.getConnectionAttributesDescription().setConnectionAttributesDescription(jsonObject.toString()); 
		 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
		 *		  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		 *        This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 */
		@Override
		public void setConfig(ConfigDataVo configData) {
	    		List<String> connectionAttributes = configData.getConnectionAttributes();
			
			    // Add the config attributes like below: 
				/*connectionAttributes.add("Username");
				connectionAttributes.add("Password");
				connectionAttributes.add("Url");
				connectionAttributes.add("ReconcileJSON");
				connectionAttributes.add("CreateUserJSON");
				connectionAttributes.add("UpdateUserJSON");
				connectionAttributes.add("RemoveAccountJSON");
				*/
				
	    		//Set the attributes that need to be encrypted 
				encryptedConnectionAttributes(configData);
				
				//Set the description of config attributes
				connectionAttributesDescription(configData); 
		}
		/**
		 * set ConnectionAttributes that need to be encrypted 
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 */
		public void encryptedConnectionAttributes(ConfigDataVo configData) {
  			    
			    List<String> encryptedConnectionAttributes = configData.getEncryptedConnectionAttributes();
			    //Add name of config attributes (defined in set  configData's connectionAttributes in setConfig()) that need to be encrypted
				//encryptedConnectionAttributes.add("Password");
		}
		/**
		 * set description of the config attributes to be used in UI for specifying thier required format
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 */
		public void connectionAttributesDescription(ConfigDataVo configData) {
			
			String connectionAttributesDescription = configData.getConnectionAttributesDescription();
			JSONObject jsonObject = new JSONObject(connectionAttributesDescription);
			
			/**
			 * Add description of the config attributes (defined in set  configData's connectionAttributes in setConfig())
			 * as below.These will be shown on the UI corresponding to these attributes as the input values format requried
			 */
					
			/*
			jsonObject.put("Username", "Provide username to connect with application");
			jsonObject.put("Password", "Provide password to connect with application");
			jsonObject.put("Url", "Provide URL to connect with application");
			
			jsonObject.put("CreateUserJSON", "SAMPLE JSON {'query':['Valid Sql Query']}");
			jsonObject.put("UpdateUserJSON", "SAMPLE JSON {'query':['Valid Sql Query']}");
			jsonObject.put("RemoveAccountJSON", "SAMPLE JSON For {'query':['Valid Sql Query']}");*/
			
			configData.setConnectionAttributesDescription(jsonObject.toString());
		}
		/**
		 * to process reconcile for users and accounts
		 * Example : to process reconcile for users and accounts , refer to the below steps
		 * step 1  : retrieve connection attributes from configData/Data
		 * step 2  : collect the data(Account,Users,Entitlements) from target system
		 * step 3  : set the data into the format accepted by connector framework's RepositoryReconService.notify()
		             sample format: finalData=[[{ACCOUNT.CUSTOMPROPERTY2=XXXX, ACCOUNT.CUSTOMPROPERTY1=XXXX, ACCOUNT.NAME=XXXX},
			                                  {ENTITLEMENT.NAME=XXXX, ENTITLEMENT.ENTITLEMENTTYPE=XXXX, ENTITLEMENT.ENTITLEMENT_VALUE=XXXX},
			 	 							  {ACCOUNT_ATTRIBUTES.ATTRIBUTE_VALUE=XXXX, ACCOUNT_ATTRIBUTES.NAME=XXXX, ACCOUNT_ATTRIBUTES.ATTRIBUTE_NAME=XXXX},
				 							  {USERS.USERNAME=XXXX}]]
		 * step 4 : call connector framework's RepositoryReconService.notify() with finalData as input param from step 3 for SSM to process recon
		 * 
		 * @param configData the configData This is a metadata that contains the details of the information required 
		          and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
	     * @param  formatterClass the formatter class - This is for future implementation hence ignore it for now 
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public void reconcile(Map<String, Object> configData, Map<String, Object> data, String formatterClass) {
			
			System.out.println("Importing data");
			List<List<Map<String, Object>>> finalData = new ArrayList<List<Map<String, Object>>>();
			List<Map<String, Object>> finalDataList = new ArrayList<Map<String, Object>>();
			JSONObject jsonObject = null;
			
			//"endpointId" is obtianed from ssm and is required in RepositoryReconService.notify() below
			Long endpointId = Long.valueOf(data.get("endpointId").toString());
		
			//process account or user reconciliation. IMPORTABLE_OBJECT is obtained from SSM and it suggest whether
			//user or account  recon is happening. Values avaialble - USER, ACCOUNT.
			if(data.get("IMPORTABLE_OBJECT").equals("USER")) {
				/* Write logic to pull users data */
			}else if(data.get("IMPORTABLE_OBJECT").equals("ACCOUNT")) {
				/*
				 * Write your own logic to pull accounts
				 * Write your own logic to pull entitlements 
				 * Write your own logic to pull accounts and entitlements relationship
				 * Build finalDataList [{ACCOUNT.CUSTOMPROPERTY2=XXXX, ACCOUNT.CUSTOMPROPERTY1=XXXX, ACCOUNT.NAME=XXXX},
				 *                      {ENTITLEMENT.NAME=XXXX, ENTITLEMENT.ENTITLEMENTTYPE=XXXX, ENTITLEMENT.ENTITLEMENT_VALUE=XXXX},
		         *                       {ACCOUNT_ATTRIBUTES.ATTRIBUTE_VALUE=XXXX, ACCOUNT_ATTRIBUTES.NAME=XXXX, ACCOUNT_ATTRIBUTES.ATTRIBUTE_NAME=XXXX},
		         *                        {USERS.USERNAME=XXXX}]
				 */
			}
			
			finalData.add(finalDataList);
			
			try {
				System.out.println("calling RepositoryReconService.notify()");
				// call notify method with finalData prepared above that contains the reconciled data 
				// for reconiliation of accounts in SSM. As explained in the parameter definition, formatterClass
				// can be ignored for now
				RepositoryReconService.notify(finalData, endpointId, formatterClass, data);
				
			} catch (Exception e) {
				 System.out.print("Exception occured in importing data " + e);
			}
			
		}
		/**
		 * This is for future implementation hence ignore it for now
		 * to check existing record for the input object USER, ACCOUNT, ENTITLEMENT, ACCOUNT_ENTITLEMENT in SSM
		 * Example : to check existing record for the input object(for account) , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : set the data with filters if any
		 * step 3 : call getObjectList
		 * step 4 : return true if object exists
		 * 
		 * @param configData the configData This is a metadata that contains the details of the information required 
		          and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
	     * @param serachableObject for serachableObject enabled for the entities USER, ACCOUNT, ENTITLEMENT, ACCOUNT_ENTITLEMENT
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@SuppressWarnings("rawtypes")
		@Override
		public Boolean checkExisting(Map<String, Object> configData, Map<String, Object> data,
				SearchableObject serachableObject) throws ConnectorException {
			Boolean recordFound=false;
			//Connect to target System With Config data 
			//Check in SSM to find user with firstname
			Map<String,Object> userMap = new HashMap<String,Object>();
			userMap.put("firstname", "firstnameissaviynt");
			//call SaviyntReadOnlyObject.getObjectList(ExposedObject sObject,Map<String, Object> filter,Integer firstResult,Integer maxResult) with below arguments in the below order
			     // ExposedObject sObject  - USERS  in this use case
			     // filter is the criteria to retrieve user object which is userMap in this use case
			     // Integer firstResult - row number  which is 1 in this use case
			     // Integer maxResult - maximum results 
	        //List resultList = SaviyntReadOnlyObject.getObjectList(ExposedObject.USERS, userMap,1,2);
	        //Return true if resultList >0
			return recordFound;
		}
		/**
		 * to create account in the target system 
		 * Example : to create account in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to create account in the target system
		 * step 4 : return the Map with metadata as explained in parameter description
		 * 
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping).
		           Metadata is nothing but set of properties from the target system to be updated in SSM for that account.
		           Example : Match the corresponding column of SSM with target system data and build sampleMetadata.
		           map sampleMetadata to provisioningData key in the result map as below and return
		           map.put("provisioningData",sampleMetadata)
		           metadata sample format : {ACCOUNT.COLUMNNAME1=XXXX, ACCOUNT.COLUMNNAME2=XXXX, ACCOUNT.COLUMNNAME3=XXXX}
		           This is optional filed. If no metadata needs to updated in SSM, it can be set to null
		           Exmaple : map.put("provisioningData",null)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map createAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//Create Account in target System 
			Map map = new HashMap();
			//build and return a map as explained in the @return Map description with this method 
			//map.put("provisioningData",metadata map);
			//Map which consists of metadata to be updated in SSM. Refer to the javadocs of this method for map format
			return map;
		}
		/**
		 * to update account in the target system
		 * Example : to update account in the target system , refer to the below steps
		 * step 1  : retrieve connection attributes from configData/Data
		 * step 2  : connect to the target system
		 * step 3  : execute the query/process with the required input to update account in the target system
		 * step 4  : return the Map with metadata as explained in parameter description
		 * 
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping).
		           Metadata is nothing but set of properties from the target system to be updated in SSM for that account.
		           Example:Match the corresponding column of SSM with target system data and build sampleMetadata.
		           map sampleMetadata to provisioningData key in the result map as below and return
		           map.put("provisioningData",sampleMetadata)
		           metadata sample format : {ACCOUNT.COLUMNNAME1=XXXX, ACCOUNT.COLUMNNAME2=XXXX, ACCOUNT.COLUMNNAME3=XXXX}
		           This is optional filed. If no metadata needs to updated in SSM, it can be set to null
	               Exmaple : map.put("provisioningData",null)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map updateAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//Update Account in target System 
			Map map = new HashMap();
			//build and return a map as explained in the @return Map description with this method 
			//map.put("provisioningData",metadata map);
			return map;
		}
		/**
		 * to lock the account in target system
		 * Example : to lock account in the target system , refer to the below steps
		 * step 1  : retrieve connection attributes from configData/Data
		 * step 2  : connect to the target system
		 * step 3  : execute the query/process with the required input to lock account in the target system
		 * step 4  : return the Map with metadata as explained in parameter description
		 * 
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM.This is for future implementation hence set it to null for now
		 *	                Example: map.put("provisioningData",null)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map lockAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//lock Account in target System 
			Map map = new HashMap();
			//build and return a map as explained in the @return Map description with this method 
			//map.put("provisioningData",metadata map);
			return map;
		}
		/**
		 * to disable account in the target system
		 * Example : to disable account in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to disable account in the target system
		 * step 4 : return the Map with metadata as explained in parameter description
		 * 
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM.This is for future implementation hence set it to null for now
		 *	                Example: map.put("provisioningData",null)
	     * @throws ConnectorException the connector exception
		 */
		@Override
		public Map disableAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//disable Account in target System 
			Map map = new HashMap();
			//build and return a map as explained in the @return Map description with this method 
			//map.put("provisioningData",metadata map);
			return map;
		}
		/**
		 * to unlock account in the target system
		 * Example : to disable account in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to disable account in the target system
		 * step 4 : return the Map with metadata as explained in parameter description
		 * 
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM.This is for future implementation hence set it to null for now
		 *	                Example: map.put("provisioningData",null)
	     * @throws ConnectorException the connector exception
		 */
		@Override
		public Map unLockAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//unLock Account in target System 
			Map map = new HashMap();
			//build and return a map as explained in the @return Map description with this method 
			//map.put("provisioningData",metadata map);
			return map;
		}
		 /**
	     * to enable account in the target system
		 * Example : to enable account in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to enable account in the target system
		 * step 4 : return the Map with metadata as explained in parameter description
		 * 
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM.This is for future implementation hence set it to null for now
		 *	                Example: map.put("provisioningData",null)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map enableAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//enable Account in target System 
			Map map = new HashMap();
			//build and return a map as explained in the @return Map description with this method 
			//map.put("provisioningData",metadata map);
			return map;

		}
		/**
		 * to terminate account in the target system
		 * Example : to terminate account in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to terminate account in the target system
		 * step 4 : return the number of records updated
		 *
	     * @param configData the configData This is a metadata that contains the details of the information required 
			  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
	          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
	     * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
			  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return the integer number of accounts terminated
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Integer terminateAccount(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// write your own logic to terminate the account in the target system
			return 1;
		}
		/**
		 * to remove account in the target system
		 * Example : to remove account in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to remove account in the target system
		 * step 4 : return the Map with metadata as explained in parameter description
		 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM.This is for future implementation hence set it to null for now
		 *	                Example: map.put("provisioningData",null)}

		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map removeAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//remove Account in target System 
			Map map = new HashMap();
			//build and return a map as explained in the @return Map description with this method 
			//map.put("provisioningData",metadata map);
			return map;
		}
		/**
		 * to add access to account in the target system
		 * Example : to add access to account in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to add access to account in the target system
		 * step 4 : return the Map with metadata as explained in parameter description
		 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM.This is for future implementation hence set it to null for now
		 *	                Example: map.put("provisioningData",null)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map addAccessToAccount(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			//Connect to target System With Config data 
			//add Access To Account in target System 
			Map map = new HashMap();
			//build and return a map as explained in the @return Map description with this method 
			//map.put("provisioningData",metadata map);
			return map;
		}
		/**
		 * to remove access to account in the target system
		 * Example : to remove access to account in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to remove access to account in the target system
		 * step 4 : return the Map with metadata as explained in parameter description
		 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM.This is for future implementation hence set it to null for now
		 *	                Example: map.put("provisioningData",null)
	     * @throws ConnectorException the connector exception
		 */
		@Override
		public Map removeAccessToAccount(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			//Connect to target System With Config data 
			//remove Access To Account in target System 
			Map map = new HashMap();
			//build and return a map as explained in the @return Map description with this method 
			//map.put("provisioningData",metadata map);
			return map;
		}
		/**
		 * to change password in the target system
		 * Example : to change password in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to change password in the target system
		 * step 4 : return true if successful
		 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean changePassword(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// write your logic to change password in the target system
			return true;
		}
		/**
	     * to create user in the target system
		 * Example : to create user in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to create user in the target system
		 * step 4 : return true if successful
		 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
	     */
		@Override
		public Boolean createUser(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// write your own logic to create user in the target system
			return true;
		}
		 /**
	   	 * to update user in the target system
	   	 * Example : to update user in the target system , refer to the below steps
	   	 * step 1 : retrieve connection attributes from configData/Data
	   	 * step 2 : connect to the target system
	   	 * step 3 : execute the query/process with the required input to update user in the target system
	   	 * step 4 : return the number of records updated
	   	 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
	   	 * @return the integer number of users updated
	   	 * @throws ConnectorException the connector exception
	   	 */
		@Override
		public Integer updateUser(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// write your own logic to update user in the target system
			return 1;
		}
		/**
		 * to create the entitlement in target system
		 * Example : to create entitlement in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to create entitlement in the target system
		 * step 4 : return the Map with metadata as explained in parameter description
		 * 
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping).
		           Metadata is nothing but set of properties from the target system to be updated in SSM for that account.
		           Example:Match the corresponding column of SSM with target system data and build sampleMetadata.
		           map sampleMetadata to provisioningData key in the result map as below and return
		           map.put("provisioningData",sampleMetadata)
		           metadata sample format : {ENTITLEMENT.COLUMNNAME1=XXXX, ENTITLEMENT.COLUMNNAME2=XXXX, ENTITLEMENT.COLUMNNAME3=XXXX}
	     * @throws ConnectorException the connector exception
		 */
		@Override
		public Map createEntitlement(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {//Connect to target System With Config data 
			// create Entitlement in target System 
			Map map = new HashMap();
			//build and return a map as explained in the @return Map description with this method 
			//map.put("provisioningData",metadata map);
			return map;
			}
		/**
		 * to update the entitlement in target system
		 * Example : to update entitlement in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to update entitlement in the target system
		 * step 4 : return the Map with metadata as explained in parameter description
		 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping).
		           Metadata is nothing but set of properties from the target system to be updated in SSM for that account.
		           Example:Match the corresponding column of SSM with target system data and build sampleMetadata.
		           map sampleMetadata to provisioningData key in the result map as below and return
		           map.put("provisioningData",sampleMetadata)
		           metadata sample format : {ENTITLEMENT.COLUMNNAME1=XXXX, ENTITLEMENT.COLUMNNAME2=XXXX, ENTITLEMENT.COLUMNNAME3=XXXX}
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map updateEntitlement(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {//Connect to target System With Config data 
			// update Entitlement in target System 
			Map map = new HashMap();
			//build and return a map as explained in the @return Map description with this method 
			//map.put("provisioningData",metadata map);
			return map;
		}
		/**
		 * to validate credentials of the given input from connection
		 * Example : to validate credentials in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to validate credentials in the target system
		 * step 4 : return true if successful, false if failure
		 * 
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean validateCredentials(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// write your own logic to validate credentials set in configData with the target system
			return true;
		}

		/**
		 * This is for future implementation hence ignore it for now
		 * to get the summary of number of records for the given input object such as accounts.It provides number of accounts,users etc
	     * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return the summary map with object and count as key ,value 
		 */
		@Override
		public Map<String, Object> getSummary(Map<String, Object> configData, Map<String, Object> data) {
			//write your logic
			Map<String, Object> map = new HashMap<String, Object>();
			//fetch account data
			//map.put("Account", 10);
			return map;
		}
		/**
		 * to remove access to entitlements in the target system
		 * Example : to remove access to entitlements in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to add access to account in the target system
		 * step 4 : return true if successful, false if failure
		 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return boolean 	 true or false which indicates success or failure 
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean removeEntitlement(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			//write your own logic to remove Entitlement in target System 
			return true;
		}
		/**
		 * to add access to entitlements in the target system
		 * Example : to add access to entitlements in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to add access to account in the target system
		 * step 4 : return true if successful, false if failure
		 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return boolean 	true or false which indicates success or failure 
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean addAccessToEntitlement(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			//write your own logic to add Access to Entitlement in target System 
			return true;
		}
		/**
		 * to remove access to entitlements in the target system
		 * Example : to remove access to entitlements in the target system , refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the query/process with the required input to add access to account in the target system
		 * step 4 : return true if successful, false if failure
		 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return boolean 	 true or false which indicates success or failure 
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean removeAccessToEntitlement(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// remove access Entitlement in target System 
			//Return
			return true;
		}
		/**
		 * to provide the firefighterId access to a system/application in target system for the inputed create account connection attributes of connection configuration in SSM
		 * provisioningData sample format: {null}
		 * Example : to add firefighterIdGrantAccess(firefighterIdGrantAccess is invoked when provisioning job is triggered in SSM) to account in the target system ,
		 			 refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the process with the required input to add access in the target system
		 * step 4 : return the map with metadata
		 * @param configData the configData This is a metadata that contains the details of the information required
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations.
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime.
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER")
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM.This is for future implementation hence set it to null for now
		 	       Example: map.put("provisioningData",null)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map firefighterIdGrantAccess(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {//Connect to target System With Config data 
			
			//write your own logic to grant firefighterId Access in target System 
			Map map = new HashMap();
			//return null in below Map   
			map.put("provisioningData", null);
			
			return map;
		}

		/**
		 * to remove the firefighterId access to a system/application in target system for the inputed create account connection attributes of connection configuration in SSM
		 * provisioningData sample format: {null}
		 * Example : to revoke firefighteridaccess(firefighterIdRevokeAccess is invoked when provisioning job is triggered in SSM) to account in the target system ,
		 			 refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the process with the required input to revoke access in the target system
		 * step 4 : return the map with metadata
		 * @param configData the configData This is a metadata that contains the details of the information required
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations.
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime.
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER")
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM.This is for future implementation hence set it to null for now
		 	       Example: map.put("provisioningData",null)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map firefighterIdRevokeAccess(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {//Connect to target System With Config data 
			
			//write your own logic to revoke firefighterId Access in target System 
			Map map = new HashMap();
			//return null in below Map   
			map.put("provisioningData", null);
			
			return map;
		}

		/**
		 * to provide the firefighterId instance access to a system/application in target system for the inputed create account connection attributes of connection configuration in SSM
		 * provisioningData sample format: {null}
		 * Example : to provide firefighterIdInstanceGrantAccess(firefighterIdInstanceGrantAccess is invoked immediately upon the task creation in SSM) to account in the target system ,
		 			 refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the process with the required input to add access in the target system
		 * step 4 : return the map with metadata
		 *
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM.This is for future implementation hence set it to null for now
		 *	       Example: map.put("provisioningData",null)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map firefighterIdInstanceGrantAccess(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {//Connect to target System With Config data 
			
			//write your own logic to grant firefighterIdInstance Access in target System 
			Map map = new HashMap();
			//return null in below Map   
			map.put("provisioningData", null);
			
			return map;
			}
		
		/**
		 * to remove the firefighterId instance access to a system/application in target system for the inputed create account connection attributes of connection configuration in SSM
		 * provisioningData sample format: {null}
		 * Example : to revoke firefighteridInstanceaccess(firefighterIdInstanceRevokeAccess is invoked immediately upon the task creation in SSM) to account in the target system ,
		 			 refer to the below steps
		 * step 1 : retrieve connection attributes from configData/Data
		 * step 2 : connect to the target system
		 * step 3 : execute the process with the required input to revoke access in the target system
		 * step 4 : return the map with metadata
		 * @param configData the configData This is a metadata that contains the details of the information required 
				  and configurations needed for establishing the connectivity to the target system and for doing provisioning and reconciliation operations. 
		          This is defined in setConfig().These appear as JSON or fields on the UI that have to be inputed at the time of creating the connection for this connector in SSM
		 * @param data contains the values (input details) of the JSON attributes/fields specified at the time of creating the connection for this connector in SSM UI.
	              current user/task/entitlement/account data referred in inputed JSON are dynamically populated at the runtime. 
				  Along with connection attributes, this parameter also contains some additional information (key value pairs) that can be used during
	              provisioning,reconciliation etc. e.g IMPORTABLE_OBJECT - This signifies whether account recon or user recon is happening. Valid values ("ACCOUNT","USER") 
	              endpointId -  contains endpoint Id for the endpoint corresponding to this connector
		 * @return Map which consists of metadata to be updated in SSM.This is for future implementation hence set it to null for now
		 *	       Example: map.put("provisioningData",null)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map firefighterIdInstanceRevokeAccess(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {//Connect to target System With Config data 
			
			//write your own logic to  revoke access to firefighterId Instance  in target System 
			Map map = new HashMap();
			//return null in below Map   
			map.put("provisioningData", null);
			
			return map;
			}
}
