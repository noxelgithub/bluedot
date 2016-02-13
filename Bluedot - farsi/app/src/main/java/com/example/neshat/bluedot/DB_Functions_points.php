<?php
 
class DB_Functions_points 
{
 
    private $conn;
 
    // constructor
    function __construct() 
	{
        require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }
 
    // destructor
    function __destruct() 
	{
         
    }
 
    /**
     * Storing new user
     * returns user details
     */
    public function sendpoints($name, $column, $totalpoints, $digipoints) 
	{
        $uuid = uniqid('', true);
        $hash = $this->hashSSHA($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt
		
 
        $stmt = $this->conn->prepare("UPDATE users SET(totalpoints, digipoints, updated_at) VALUES(?, ?, NOW()) WHERE name = name");
        $stmt->bind_param("sssss", $uuid, $name, $column, $totalpoints, $digipoints);
        $result = $stmt->execute();
        $stmt->close();
 
        
    }
 
    /**
     * Get user by email and password
     */
  
 
}
 
?>