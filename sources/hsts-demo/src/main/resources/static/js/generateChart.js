/**
 * Created by QUYHKSE61160 on 11/13/15.
 */
function showChart(){
    var parent = document.getElementById("superParent");
    var child = document.getElementById("child");
    parent.style.display = "";
    child.style.display = "";
    function obj (label, y) {
        this.label = label,
        this.y = y
    }
    var calories = [];
    var estimate = [];

    for(var i = 0; i < listDataOfPatient.listPatientTime.length; i++){
        calories.push(new obj(listDataOfPatient.listPatientTime[i],parseInt(listDataOfPatient.listPatientCalories[i])));
        estimate.push(new obj(listDataOfPatient.listPatientTime[i],parseInt(listDataOfPatient.kcalEstimate)));
    }
    var chart = new CanvasJS.Chart("openChart",
        {
            animationEnabled: true,
            title:{
                text: "History Patient Practice"
            },
            axisX:{
                labelAngle: -30
            },
            data: [
                {
                    name: "Patient",
                    type: "spline", //change type to bar, line, area, pie, etc
                    showInLegend: true,
                    dataPoints: calories
                },
                {
                    name: "Estimate",
                    type: "spline",
                    showInLegend: true,
                    dataPoints: estimate
                }
            ]
        });

    chart.render();
}

function hidePracticePopup(target) {

    target.style.display = "none";
    document.getElementById("child").style.display = "none";

}