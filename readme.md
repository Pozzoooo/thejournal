## The Journal Sample

In this sample I've tried to create a very flexible approach, where feeds 
and filters could be easily switched without a big effort.

#### Architecture

I've separated in 3 layers:
- Client;  
Where the web requests are being handled.

- Business;  
To keep any important logic.

- View.  
Here I used the MVVM pattern and Activity + Fragments, which should create 
a design very decoupled, maintenance could be handled by more then 1 
 developer without too much conflict and rearrangement should't be a big 
 hassle.  
The design is also prepared for a tablet implementation, not many changes 
should be necessary to get something reasonable.

A persistence layer is not defined yet, as I skipped it for time limit 
reason.  

Also as part of the architecture, I've created a few base implementations 
which the idea is to help keeping the approach similar between new screens 
which will come in the future.  

Another important part of the architecture is the dependency injection, 
dagger is being heavily used, new dependencies should rely on its graph 
so the project can be kept organized.

#### Some very important points where the project miss out

- Layout improvement;  
I haven't spent much time on it, as I believe this is much about taste, 
 but I've tried to leave it open for changing.
 
- Error handling;  
I've made it very basic and Kotlin is helping me to avoid the NullPointers, 
but there is still a lot that can go wrong, a more elaborated error handling 
would be necessary.

- Database;  
That's definitely something that couldn't miss out on this kind of project, 
but I haven't tried it, as it would demand much more time.

- Todo list;  
I left a few items around which could be improved.

- Test coverage;  
I built it to be enable to test all business and viewModel layout, plus 
client layer with integration test, but I didn't have the time to finish it, 
pleas, don't get me wrong at this point, I believe test are really important 
and would say that the project cannot continue until the coverage is in good 
shape.

- Bug tracking/analytics.  
No app should go live with some monitoring tools in my opinion, specially 
bug tracking.
