<?php

    require 'dbts.php';
    
    if($_SERVER['REQUEST_METHOD']=='POST'){
    
        try {
            $dbh = new PDO("mysql:host=$hostname;
                           dbname=craigk_ticket", $username, $password);
            //echo "Connected to database.";
        } catch (PDOException $e) {
            echo $e->getMessage();
        }
        
        $request = json_decode(file_get_contents('php://input'));
        
        $firstName = (string)$request->fname;
        $lastName = (string)$request->lname;
        $urgency = (string)$request->urgency;
        $description = (string)$request->description;
        $domain = (string)$request->domain;
        $email = (string)$request->email;

        $sql = "INSERT INTO `craigk_ticket`.`Tickets` (`firstname`, `lastname`, `urgency`, `description`, `domain`, `email`)
            VALUES (:firstname, :lastname, :urgency, :description, :domain, :email)";
            
        $statement = $dbh->prepare($sql);
        
        $statement->bindValue(':firstname', $firstName, PDO::PARAM_STR);
        $statement->bindValue(':lastname', $lastName, PDO::PARAM_STR);
        $statement->bindValue(':urgency', $urgency, PDO::PARAM_STR);
        $statement->bindValue(':description', $description, PDO::PARAM_STR);
        $statement->bindValue(':domain', $domain, PDO::PARAM_STR);
        $statement->bindValue(':email', $email, PDO::PARAM_STR);
        
        $statement->execute();
        
        $to = (string)$request->email;
        $tech = 'ckoch3@mail.greenriver.edu';
        $subject = "Hello" . (string)$request->fname . (string)$request->lname .
                    "\nThank you for submitting your ticket. \nDescription: " .
                    (string)$request->description . ". \nPriority: " .
                    (string)$request->urgency . ".
                    \nA technician will contact you shortly.";
                    
        $techSubject = "A new ticket has been submitted. \n"
                        . (string)$request->fname . (string)$request->lname .
                        "(" . (string)$request->domain . "). \nDescription: " .
                        (string)$request->description . ". \nPriority is " .
                        (string)$request->urgency . ". \nContact " . (string)$request->fname .
                        " at " . (string)$request->email .
                        ". \nView ticket at http:/craigkoch.greenrivertech.net/login.php";
                        
        $message = "Your Ticket Submission";
        $techMessage = "New Ticket Submission";
        mail($to, $message, $subject);
        mail($tech, $techMessage, $techSubject);
        
        $response['success'] = "success";
        
        echo json_encode($response);
        
    }
    
?>