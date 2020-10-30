package main.java.com.external.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.saviynt.ssm.abstractConnector.BaseConnectorSpecification;
import com.saviynt.ssm.abstractConnector.ConfigDataVo;
import com.saviynt.ssm.abstractConnector.RepositoryReconService;
import com.saviynt.ssm.abstractConnector.SearchableObject;
import com.saviynt.ssm.abstractConnector.exceptions.ConnectorException;
import com.saviynt.ssm.abstractConnector.exceptions.InvalidAttributeValueException;
import com.saviynt.ssm.abstractConnector.exceptions.InvalidCredentialException;
import com.saviynt.ssm.abstractConnector.exceptions.MissingKeyException;
import com.saviynt.ssm.abstractConnector.exceptions.OperationTimeoutException;

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
		 
			return "SAMPLE";
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
		 * to test the connection of the input target system configured in connection on SSM
		 * 
		 * @param configData the config data for target connection information holds connection attributes
		 * @param filterData the Input data for the configured attributes through setConfig from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception throws connector exception
		 * @throws InvalidCredentialException the invalid credential exception
		 * @throws InvalidAttributeValueException the invalid attribute value exception
		 * @throws OperationTimeoutException the operation timeout exception
		 * @throws MissingKeyException the missing key exception
		 */
		@Override
		public Boolean test(Map<String, Object> configData, Map<String, Object> filterData) throws ConnectorException,
				InvalidCredentialException, InvalidAttributeValueException, OperationTimeoutException, MissingKeyException {
			//logic to test connection
			return true;
		}

		/**
		 * to process reconciliation for users and accounts by extracting the input for Recon from SSM Connection attributes when initiated a Recon job in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param dataFromEcm the Input data for the configured attributes through setConfig from connection 
		 * @param formatterClass the formatter class
		 */
		@Override
		public void reconsile(Map<String, Object> configData, Map<String, Object> dataFromEcm, String formatterClass) {
			List<List<Map<String, Object>>> finalData = new ArrayList<List<Map<String, Object>>>();
			List<Map<String, Object>> finalDataList = new ArrayList<Map<String, Object>>();
			JSONObject jsonObject = new JSONObject(dataFromEcm.get("AccountReconJSON").toString());
			Map<String, Object> filterData = jsonObject.toMap();
			System.out.println(filterData);
			finalData.add(finalDataList);
			try {
				RepositoryReconService.notify(finalData, null, formatterClass, dataFromEcm);
			} catch (Exception e) {
				 
			}
			
			 
			
		}

		/**
		 * to check existing record for the input object such as users inputed from SSM connection attributes.
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param filterData the Input data for the configured attributes through setConfig from connection 
		 * @param serachableObject the serachable object to retrieve the inputed entity information from the source system
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean checkExisting(Map<String, Object> configData, Map<String, Object> data,
				SearchableObject serachableObject) throws ConnectorException {
			 
			Boolean recordFound=false;
			//Connect to target System With Config data 
			//Check in target System 
			//Return 
			return recordFound;
		}

		/**
		 * to create the account in target system for the inputed create account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean createAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			Boolean recordCreated=false;
			//Connect to target System With Config data 
			//Create in target System 
			//Return 
			return recordCreated;
		}

		/**
		 * to update the account in target system for the inputed update connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		* @return the integer number of accounts updated
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Integer updateAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to lock the account in target system for the inputed lock account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean lockAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to disable the account in target system for the inputed disable connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection tion information
		 * @param data the data for input data from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean disableAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to unlock the account in target system for the inputed unlock connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean unLockAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to enable the account in target system for the inputed enable account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean enableAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to terminate the account in target system for the inputed terminate account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection  
		 * @return the integer number of accounts terminated
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Integer terminateAccount(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to remove the account in target system for the inputed remove account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		 * @return the integer number of accounts removed
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Integer removeAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to add access to the account in target system for the inputed addAccess to account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		 * @return the integer access granted count
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Integer addAccessToAccount(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to remove the account access in target system for the inputed remove account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		 * @return the integer
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Integer removeAccessToAccount(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to change the password in target system for the inputed change password connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean changePassword(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to create the user in target system for the inputed create user connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean createUser(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to update the user in target system for the inputed update user connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection  
		 * @return the integer
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Integer updateUser(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to update the entitlement in target system for the inputed account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection  
		 * @return the integer
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Integer updateEntitlement(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to create the entitlement in target system for the inputed create entitlement connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean createEntitlement(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to validate credentials for the inputed crednetials connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection  
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean validateCredentials(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to get the summary of number of records for the given input object such as accounts.It provides number of accounts,users etc
		 *
	     * @param configData the config data for target connection information holds connection attributes
		 * @param data the Input data for the configured attributes through setConfig from connection 
		 * @return the summary map with object and count as key ,value 
		 */
		@Override
		public Map<String, Object> getSummary(Map<String, Object> configData, Map<String, Object> data) {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to set the config with attributes needed for creating a connection in SSM.
		 * The attributes defined in setConfig are the attributes that would dynamically
		 * poupulate on connection creation under SSM
		 *
		 * @param configData the new config for input connection attributes from
		 *                   connection created
		 */
		@Override
		public void setConfig(ConfigDataVo configData) {
			// TODO Auto-generated method stub

		}
	    
}
