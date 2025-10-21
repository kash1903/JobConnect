
        const quotes = [
            // English
            "“Choose a job you love, and you will never have to work a day in your life.” — Confucius",
            "“Success in your career begins with passion, discipline, and purpose.”",
            "“Don’t just find a job — build a career that defines you.”",
            "“Dream big, work smart, and stay humble. That’s the formula for lasting success.”",
            "“Opportunities don’t happen. You create them.” — Chris Grosser",


            // Hindi
            "“कामयाबी उन्हीं को मिलती है, जिनके सपनों में जान होती है।”",
            "“सिर्फ नौकरी मत ढूंढो, अपने करियर की पहचान बनाओ।”",
            "“हर काम में इमानदारी ही असली पहचान है।”",
            

           
            // Bengali
            "“কাজই মানুষের আসল পরিচয়।”",        
            "“যে কাজ ভালোবাসে, সে জীবনে একদিনও কাজ করে না।”",            
            "“চাকরি মানে শুধু অর্থ নয়, এটা স্বপ্ন পূরণের পথ।”",            
            "“পরিশ্রমই সাফল্যের একমাত্র চাবিকাঠি।”",            
            "“নিজের যোগ্যতাকে প্রমাণ করার সুযোগই হল সঠিক চাকরি।”"
          

        ];

        let index = 0;
        const quoteDisplay = document.getElementById("quoteDisplay");

        setInterval(() => {
            index = (index + 1) % quotes.length;
            quoteDisplay.style.opacity = 0;
            setTimeout(() => {
                quoteDisplay.innerHTML = quotes[index];
                quoteDisplay.style.opacity = 1;
            }, 1000);
        }, 4000); // changes every 4 seconds
  
