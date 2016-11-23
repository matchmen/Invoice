var map = {};
map['menu_2_1'] = 0;
map['menu_2_2'] = 0;
map['menu_2_3'] = 0;
map['menu_2_4'] = 0;

function menu_fun(str) {
	if(map[str]==1){
		map[str] = 0;
		document.getElementById(str).style.display = "none";
		document.getElementById('menu_2_img_'+str.substr(str.length-1,1)).setAttribute('src', 'images/layout_button_down.gif');
	}else{
		map[str]=1;
		document.getElementById(str).style.display = "block";
		document.getElementById('menu_2_img_'+str.substr(str.length-1,1)).setAttribute('src', 'images/layout_button_up.gif');
	}
}

function tab_change(str){
	var ifra = document.getElementById("ifra").setAttribute('src',str);
}