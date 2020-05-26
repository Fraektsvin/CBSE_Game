Controls:
* Movement - WASD
* Shoot - Left Mouse Button
* Menu - Escape


Enable load/unload of modules
* Navigate to the updates.xml file
  * StealMySheep/netbeans_site/updates.xml
  * Copy the updates.xml absolute file path
  
* Insert the updates.xml path into silentUpdateModule
  * Open the bundles.properties file
    * StealMySheep/SilentUpdate/src/main/resources/org/netbeans/modules/autoupdate/silentupdate/resources/Bundle.properties
  * Insert the updates.xml path as the value for; org_netbeans_modules_autoupdate_silentupdate_update_center
  
* Insert the updates.xml path into ModuleManager in Island module
  * Open the ModuleManager.java file
    * StealMySheep/Island/src/main/java/group12/stealmysheep/Manager/ModuleManager.java
  Insert the updates.xml path as the value for the String attribute; updateXML
