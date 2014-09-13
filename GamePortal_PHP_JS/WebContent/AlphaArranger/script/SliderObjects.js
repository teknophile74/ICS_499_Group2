  var g = 15;
  var flag = 0;
  var moved = 0;

  function blank() {}

  function imat(loc) {
    if ((((g-1) == loc) && ((g%3) != 0)) || (((g+1) == loc) && ((g%3) != 2)) || ((g-3) == loc) || ((g+3) == loc)) {
      swap(g, loc);
      g = loc;
    }
    check();
  }

  function dir(move) {

    if (move == 0) {
      if ((g == 8) || (g == 7) || (g == 5) || (g == 4) || (g == 2) || (g == 1)) {
        swap (g, g-1);
        g -= 1;
        check();
      }
      return;
    }
    if (move == 1) {
      if ((g == 6) || (g == 7) || (g == 3) || (g == 4) || (g == 0) || (g == 1)) {
        swap (g, g+1);
        g += 1;
        check();
      }
      return;
    }
    if (move == 2) {
      if ((g == 3) || (g == 4) || (g == 5) || (g == 6) || (g == 7) || (g == 8)) {
        swap (g, g-3);
        g -= 3;
        check();
  
      }
      return;
    }
    else {
      if ((g == 3) || (g == 4) || (g == 5) || (g == 0) || (g == 1) || (g == 2)) {
        swap (g, g+3);
        g += 3;
        check();
      }
    }
    
  }

  function doit() {
    var temp = 0;
    while (temp < 5) {
      randomize();
      temp++;
    }
  }

  function randomize() {
    flag = 0;
    imat(7);
    imat(4);
    while (moved < 200) {
      imat(Math.round((Math.random() * g)));
    }
    moved = 0;

    //for (x = 0; x < 3; x++) {
      //swap(0, Math.round((Math.random() * 7)));
      //swap(1, Math.round((Math.random() * 7)));
      //swap(2, Math.round((Math.random() * 7)));
      //swap(3, Math.round((Math.random() * 7)));
      //swap(4, Math.round((Math.random() * 7)));
      //swap(5, Math.round((Math.random() * 7)));
      //swap(6, Math.round((Math.random() * 7)));
      //swap(7, Math.round((Math.random() * 7)));
    //}
    flag = 1;
  }

  function swap (a, b) {
    var temp = document.images[a].src;
    document.images[a].src = document.images[b].src;
    document.images[b].src = temp;

  }

  function check () {
    if (flag != 1) {
      moved++;
      return;
    }
    var count = 0;
    var i = 0;
    for (i; i < g; i++) {
      //alert(document.images[i].src.charAt(document.images[i].src.length - 5));
      if (document.images[i].src.charAt(document.images[i].src.length - 5) == (i + 1)) {
        count++;
        //alert("I am incrementing for position " + i);
      }
    }
    if (count < g) {
      //alert(count + " number(s) are correct");
    }
    else {
      alert("Hooray!! You are a Winner!");
      flag=0;
    }
  }

  function doh(msg) {
    alert(msg);
  }