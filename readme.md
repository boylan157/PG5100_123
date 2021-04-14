#Exam in PG5100 candidate number: 123

###My solution:
This is my solution for the exam. I've made a "Gatcha" game, where the user can get pokemon from lootboxes. 
I've built this solution using jpa for storing entities, spring services to handle my beans. and jsf to build a frontend.
The application can be ran from the class ***LocalApplicationRunner*** in the test folder inside to frontend module.
You can the open the application on ***localhost:8080***. you will be shown a list of available pokemon and the options
to either login or sign up. When logging in you will have 3 lootboxes you can open wich will drop 3 
"random"(this will only drop pokemon with pokedexID between 1 and 9 because instead of picking pokemon cronologically i chose my favourites,
i would of course change this if i had more time and it was a "real" project) pokemon each. User can also buy more lootboxes for 100 pokemon dollars,
if they have the money. If they dont they can get it by pressing the button that says "Get money". This would not be so easy in a "real"
application but for this exam its very convienent for trying it out and testing the different functions. 

### Requirements
* According to Jacoco's coverage report i have 90% coverage in the "Cov"-column, next to "Missed Instructions"
* I have completed Requirement R1, R2 and R3. I did not manage to do R4 and R5.


