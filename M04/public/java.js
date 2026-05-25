document.querySelectorAll('.check').forEach(chk => {
    chk.addEventListener('change',()=>{
        //const targetID=chk.dataset.target;
        //const img=document.getElementById(targetID);

        const img=document.getElementById(chk.dataset.target);

        if(chk.checked){
            img.classList.remove('oculto');
            img.classList.add('noculto');
        }else{
            img.classList.remove('noculto');
            img.classList.add('oculto');
        }
    }
    );    
});

document.querySelectorAll('.check').forEach(chk =>{
    chk.addEventListener('change',()=>{
        const Principal=document.getElementById(chk.dataset.target);
        if(chk.checked){
            Principal.classList.remove('oculto1');
            Principal.classList.add('visible');
        }else{
            Principal.classList.remove('visible');
            Principal.classList.add('oculto1');
            
        }
    })
})