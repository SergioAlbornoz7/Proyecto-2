document.querySelectorAll('.check').forEach(chk => {
    chk.addEventListener('change',()=>{
        const targetID=chk.dataset.target;
        const img=document.getElementById(targetID);

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