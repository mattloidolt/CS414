<?php
    /**********************************************************************
     Start of PHP
     **********************************************************************/
    
    // establish connection
    $con = mysql_connect("faure.cs.colostate.edu","loidolt","827375410");
    if (!$con) {
        die('Could not connect: ' . mysql_error());
    }
    
    mysql_select_db("loidolt", $con);
    
    $safe_name = mysql_real_escape_string($_POST["name"]);
    $safe_phone = mysql_real_escape_string($_POST["phone"]);
    $safe_address = mysql_real_escape_string($_POST["address"]);
    $safe_nameOnCard = mysql_real_escape_string($_POST["nameOnCard"]);
    $safe_CCnum = mysql_real_escape_string($_POST["CCnum"]);
    $safe_expDate = mysql_real_escape_string($_POST["expDate"]);
    $safe_order = mysql_real_escape_string($_POST["order"]);
    
    $insertNew = "INSERT INTO orders VALUES ('". $safe_name . "','". $safe_phone . "','". $safe_address . "','". $safe_nameOnCard . "', '". $safe_CCnum . "', '" . $safe_expDate . "','" . $safe_order . "')";
    if (!mysql_query($insertNew,$con)) {
        die('Error: ' . mysql_error());
    }
    echo "success" ;
    // close connection to database
    mysql_close($con) ;
?>