/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
console.log($("ui.icon.sign.in"));
$("ui.header").on("click",function(){
    console.log("clicked")

});


if(document.querySelector(".ui.modal.erreur")!= null){
    
    
    $(".ui.modal.erreur").modal("show");
    
    
}

if(document.querySelector(".voirPasse")!= null){
    
   document.querySelector(".voirPasse").addEventListener("click",function(){
       if(document.getElementsByName("password")[0].type == "password"){
           document.getElementsByName("password")[0].type = "text"
           this.innerHTML = "Cacher le mot de passe?"
       }else{
           document.getElementsByName("password")[0].type = "password"
       this.innerHTML = "Voir le mot de passe?"
       }
   }) 
    
    
    
}

document.querySelector(".ui.button.cancel").addEventListener("click",function(){
    $(".ui.modal.active").modal("hide")
})
if(document.querySelector(".ui.icon.log.out")!= null){
    document.querySelector(".ui.icon.log.out").addEventListener("click",function(){
        $(".ui.modal.confirmLogout").modal("show")
    })
}

if(document.querySelector(".ui.icon.sign.in")!= null){
    
document.querySelector(".ui.icon.sign.in").addEventListener("click",function(){
 
       $(".ui.modal.connexion").modal("show");

});
}

if(document.querySelector(".ui.button.addClient")!= null){
    
document.querySelector(".ui.button.addClient").addEventListener("click",function(){
 
       $(".ui.modal.addClient").modal("show");

});
}


if(document.querySelector(".ui.button.addProduit")!= null){
    
document.querySelector(".ui.button.addProduit").addEventListener("click",function(){
 
       $(".ui.modal.addProduit").modal("show");

});
}

if(document.querySelector(".ui.button.addCommande")!= null){
    
document.querySelector(".ui.button.addCommande").addEventListener("click",function(){
 
       $(".ui.modal.addCommande").modal("show");

});
}



   
document.querySelectorAll(".ui.button.deleteProd").forEach(e=>{
   e.addEventListener("click",function(e){
       
        
        document.querySelector(".lienSuppression").href= "/Test/ProduitServlet?idToDelete=" + this.childNodes[0].innerHTML
     //   document.querySelector(".idToDelete").value = this.childNodes[0].innerHTML
        
      //  alert(document.querySelector(".idToDelete").value)
        
        $(".ui.modal.confirmDeleteProduit").modal("show");

})
    
})




   
document.querySelectorAll(".ui.button.deleteClient").forEach(e=>{
   e.addEventListener("click",function(e){
       
        console.log(this.childNodes[0].innerHTML)
        
        document.querySelector(".lienSuppression").href= "/Test/ClientServlet?idToDelete=" + this.childNodes[0].innerHTML
     //   document.querySelector(".idToDelete").value = this.childNodes[0].innerHTML
        
      //  alert(document.querySelector(".idToDelete").value)
        
        $(".ui.modal.confirmDeleteClient").modal("show");

});
    
})

   
document.querySelectorAll(".ui.button.deleteCommande").forEach(e=>{
   e.addEventListener("click",function(e){
       
        console.log(this.childNodes[0].innerHTML)
        
        document.querySelector(".lienSuppression").href= "/Test/CommandeServlet?idToDelete=" + this.childNodes[0].innerHTML
     //   document.querySelector(".idToDelete").value = this.childNodes[0].innerHTML
        
      //  alert(document.querySelector(".idToDelete").value)
        
        $(".ui.modal.confirmDeleteCommande").modal("show");

});
    
})
  
document.querySelectorAll(".ui.button.addToPanier").forEach(e=>{
   e.addEventListener("click",function(e){
       
        console.log(this.childNodes[0].innerHTML)
        
       // document.querySelector(".lienSuppression").href= "/Test/ClientServlet?idToDelete=" + this.childNodes[0].innerHTML
        document.querySelector(".idProduitToAdd").value = this.childNodes[0].innerHTML
        
      //  alert(document.querySelector(".idToDelete").value)
        
        $(".ui.modal.tiny.addAchat").modal("show");

});});
    