//Script to get all vaild available url from txt
const go = document.getElementById("go");

// reserved for other use
let filted = []; 
let fail = [];

// string to store success / fail message
let wordSuccess = "";
let wordFailed = "";

class WebLink{ // class method to implementing
     
    constructor(name, url){  
        this.webName = name;
        this.webUrl = url;
    }

}

function getInfo() {
    return new Promise((resolve, reject) => {
        var request = new XMLHttpRequest();
        request.open('GET', 'testt.txt', false); // if file is a raw txt with line
        request.send();
        
        let videoList = request.responseText.split("\n"); //Array list of the video list
        let classList = [];


        for(let i = 0; i < videoList.length ; i += 2){
            classList.push(new WebLink (videoList[i], videoList[i+1]));
        }

        /*
        // if no url name
        for(let i = 0; i < videoList.length ; i ++){
            classList.push(new WebLink ("", videoList[i]));
        }
        */

        videoList = new Array();
        
        resolve(classList);
        
    })
}

function youTubeGetID2(url){
    return new Promise((resolve, reject) =>{
        url = url.split(/(vi\/|v%3D|v=|\/v\/|youtu\.be\/|\/embed\/)/);
        undefined !== url[2] ? resolve(url[2].split(/[^0-9a-z_\-]/i)[0]) : resolve(url[0]);
    });

}

function checkValid(id,li) {
    return new Promise((resolve, reject) =>{
        var img = new Image();
        img.src = "http://img.youtube.com/vi/" + id + "/mqdefault.jpg";
        img.onload = function () {
         this.width === 120 ? resolve(0) : resolve(1);                              
        }

    });

}

function printFinal(text){
    return new Promise((resolve, reject) =>{
        console.log(text);     
    });

}

function add(id, item){
    return new Promise((resolve, reject) =>{
        if (id == "suc"){ // store succcess info
            filted.push(item);
            wordSuccess += item.webUrl + "\n";
            resolve(1);
        }
        else{ // store failed info
            fail.push(item);
            wordFailed += item.webUrl + "\n";
            resolve(0);

        }
        
    });

}

function download(filename, type,data) { //download txt file function
    return new Promise((resolve, reject) =>{
        var element = document.createElement('a');

        if(type === "txt"){
            element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(data));
        }
        else if(type === "json"){
            element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(JSON.stringify(data)));
        }
        
        element.setAttribute('download', filename);
    
        element.style.display = 'none';
        document.body.appendChild(element);
    
        element.click();
    
        document.body.removeChild(element);
        
        resolve();
        
    });
    
}


  
async function doing() {
    go.disabled = true;
    let result = await getInfo();      

    for(let i = 0; i < result.length; i++){
        console.log(i);
        youTubeGetID2(result[i].webUrl)
        .then(e => checkValid(e, result[i]))
        .then(b => {b != 0 ? add("suc", result[i]) : add("ff", result[i]) });               

    }

    printFinal(filted)
    .then(printFinal(fail))
    .then(printFinal("done"))
    .then(go.disabled = false);

};

function doSome(){
    download("tolo", "txt", wordSuccess);
}

doing();
