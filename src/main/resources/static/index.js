function setlimit()
{
	const limit = document.getElementById("limit");
	const limitval = parseInt(limit.value);
	console.log(limitval);
	 
	fetch('http://localhost:8080/setupperlimit/'+limit.value, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        } 
    });
  
};



function loadresult()
{
	fetch('http://localhost:8080/getdivisiblenumber', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        } 
    }).then((res) => res.json()).then((data) => {
        console.log(data);
        document.getElementById("result").innerHTML=data.message;
        }).catch((error) => {
            console.log(error);
        });
};


 