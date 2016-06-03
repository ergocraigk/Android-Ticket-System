/**
*The MIT License (MIT)
*Copyright (c) 2016  Amaninder Singh, Scott Evenson, Craig Koch
*
*Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
*documentation files (the "Software"), to deal in the Software without restriction, including without limitation the 
*rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit 
*persons to whom the Software is furnished to do so, subject to the following conditions:
*
*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
*WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
*COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR 
*OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
<?php

    require 'dbts.php';

    if($_SERVER['REQUEST_METHOD']=='POST'){
        try {
            $dbh = new PDO("mysql:host=$hostname;
                           dbname=craigk_ticket", $username, $password);
            //echo "Connected to database.";
        } catch (PDOException $e) {
            //echo $e->getMessage();
        }
        
        $request = json_decode(file_get_contents('php://input'));
        
        // Check if the user name exists. If one result is returned then there are no duplicates and
            // a result. User is then confirmed.
            $sql = "select COUNT(*) from `craigk_ticket`.`login` where username = :username and password = :password";
            $stmt = $dbh->prepare($sql);
            $stmt->bindValue("username", (string)$request->username, PDO::PARAM_STR);
            $stmt->bindValue("password", (string)$request->password, PDO::PARAM_STR);
            $stmt->execute();
            $count = $stmt->fetchColumn();
            
            $sqlid = "select username from `craigk_ticket` . `login` where username = :username";
            $stmtid = $dbh->prepare($sqlid);
            $stmtid->bindValue("username", (string)$request->username, PDO::PARAM_STR);
            $stmtid->execute();
            $id = $stmtid->fetch();
            
            $sqllvl = "select accesslevel from `craigk_ticket` . `login` where username = :username";
            $stmtlvl = $dbh->prepare($sqllvl);
            $stmtlvl->bindValue("username", (string)$request->username, PDO::PARAM_STR);
            $stmtlvl->execute();
            $al = $stmtlvl->fetchColumn();
            
            $response['success'] = null;
            $response["userID"] = $id;
            $response["accessLevel"] = $al;
            
            if($count == 1){
                $response['success'] = "true";
                echo json_encode($response);
            }else{
                $response['success'] = "false";
                echo json_encode($response);
            }
    }
?>