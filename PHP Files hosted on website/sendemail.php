<?php

$email_to = $_POST["email"];
$name = $_POST["fullname"];
$email_from = "furryformulamaths@w1480440fyp.co.uk";
$message = "Your child has registered for an account with us at FurryFormula, an AR maths android application aimed at teaching children maths in an augmented reality world! For more information and the AR Markers to help your child on their way to their journey, please go to: http://w1480440fyp.co.uk/ARMarkers.pdf ";
$email_subject = "Thank You for Using FurryFormula!";
$headers = "From: " . $email_from . "\n";
$headers .= "Reply-To: " . $email_from . "\n";

$message = "Name: ". $name . "\r\nMessage: " . $message;

ini_set("sendmail_from", "$email_from");
$sent = mail($email_to, $email_subject, $message, $headers, "-f" .$email_from);
if ($sent)
{
echo "succcess";
} else {
echo "failed";
}
?>