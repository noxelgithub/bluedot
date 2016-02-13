<?php
 
require_once 'include/DB_Functions_points.php';
$db = new DB_Functions_points();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['name']) && isset($_POST['column']) && isset($_POST['totalpoints']) && isset($_POST['digipoints'])) 
{
 
    // receiving the post params
    $name = $_POST['name'];
    $column = $_POST['column'];
    $totalpoints = $_POST['totalpoints'];
	$digipoints = $_POST['digipoints'];
 
    // check if user is already existed with the same email
   
        // create a new user
        $user = $db->sendpoints($name, $column, $totalpoints, $digipoints);
        if ($user) {
            // user stored successfully
            $response["error"] = FALSE;
            $response["uid"] = $user["unique_id"];
            $response["user"]["name"] = $user["name"];
            $response["user"]["column"] = $user["column"];
			$response["user"]["totalpoints"] = $user["totalpoints"];
            $response["user"]["digipoints"] = $user["digipoints"];
            $response["user"]["created_at"] = $user["created_at"];
            $response["user"]["updated_at"] = $user["updated_at"];
            echo json_encode($response);
        } else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred in registration!";
            echo json_encode($response);
        }
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters (name, email or password) is missing!";
    echo json_encode($response);
}
?>