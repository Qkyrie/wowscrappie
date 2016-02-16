var Tier18LootDistribution = function () {

    var self = this;

    self.specs = ko.observableArray(
        [
            {
                "dpsColor": "red",
                "className": "Death Knight",
                "spec": "1H Frost",
                "token": "Vanquisher",
                "normalDps": 62689,
                "fourP17": 64887,
                "twoP17twop18": 65155,
                "fourP18": 72641,
                "twoP18": 65032,
                "normalTotwoP18Gain": "3,74",
                "twoP17twoP18Gain": "3,93",
                "combiTo4P18Gain": "11,49",
                "normalTo4P18Gain": "15,88",
                "fourP17ToCombiGain": "0,41",
                "fourP17ToFourP18Gain": "11,95"
            },
            {
                "dpsColor": "red",
                "className": "Death Knight",
                "spec": "2H Frost",
                "token": "Vanquisher",
                "normalDps": 57812,
                "fourP17": 59858,
                "twoP17twop18": 60202,
                "fourP18": 66972,
                "twoP18": 59941,
                "normalTotwoP18Gain": "3,68",
                "twoP17twoP18Gain": "4,13",
                "combiTo4P18Gain": "11,25",
                "normalTo4P18Gain": "15,84",
                "fourP17ToCombiGain": "0,57",
                "fourP17ToFourP18Gain": "11,88"
            },
            {
                "dpsColor": "red",
                "className": "Death Knight",
                "spec": "Unholy",
                "token": "Vanquisher",
                "normalDps": 56693,
                "fourP17": 58935,
                "twoP17twop18": 57875,
                "fourP18": 64564,
                "twoP18": 56827,
                "normalTotwoP18Gain": "0,24",
                "twoP17twoP18Gain": "2,08",
                "combiTo4P18Gain": "11,56",
                "normalTo4P18Gain": "13,88",
                "fourP17ToCombiGain": "-1,80",
                "fourP17ToFourP18Gain": "9,55"
            },
            {
                "dpsColor": "orange",
                "className": "Druid",
                "spec": "Balance",
                "token": "Vanquisher",
                "normalDps": 55676,
                "fourP17": 60714,
                "twoP17twop18": 64634,
                "fourP18": 64607,
                "twoP18": 61522,
                "normalTotwoP18Gain": "10,50",
                "twoP17twoP18Gain": "16,09",
                "combiTo4P18Gain": "-0,04",
                "normalTo4P18Gain": "16,04",
                "fourP17ToCombiGain": "6,46",
                "fourP17ToFourP18Gain": "6,41"
            },
            {
                "dpsColor": "orange",
                "className": "Druid",
                "spec": "Feral",
                "token": "Vanquisher",
                "normalDps": 57207,
                "fourP17": 61645,
                "twoP17twop18": 61669,
                "fourP18": 65598,
                "twoP18": 60118,
                "normalTotwoP18Gain": "5,09",
                "twoP17twoP18Gain": "7,80",
                "combiTo4P18Gain": "6,37",
                "normalTo4P18Gain": "14,67",
                "fourP17ToCombiGain": "0,04",
                "fourP17ToFourP18Gain": "6,41"
            },
            {
                "dpsColor": '#69CCF0',
                "className": "Mage",
                "spec": "Arcane",
                "token": "Vanquisher",
                "normalDps": 63940,
                "fourP17": 68524,
                "twoP17twop18": 72283,
                "fourP18": 76229,
                "twoP18": 70412,
                "normalTotwoP18Gain": "10,12",
                "twoP17twoP18Gain": "13,05",
                "combiTo4P18Gain": "5,46",
                "normalTo4P18Gain": "19,22",
                "fourP17ToCombiGain": "5,49",
                "fourP17ToFourP18Gain": "11,24"
            },
            {
                "dpsColor": '#69CCF0',
                "className": "Mage",
                "spec": "Fire",
                "token": "Vanquisher",
                "normalDps": 52039,
                "fourP17": 54909,
                "twoP17twop18": 57087,
                "fourP18": 59455,
                "twoP18": 55759,
                "normalTotwoP18Gain": "7,15",
                "twoP17twoP18Gain": "9,70",
                "combiTo4P18Gain": "4,15",
                "normalTo4P18Gain": "14,25",
                "fourP17ToCombiGain": "3,97",
                "fourP17ToFourP18Gain": "8,28"
            },
            {
                "dpsColor": '#69CCF0',
                "className": "Mage",
                "spec": "Frost",
                "token": "Vanquisher",
                "normalDps": 65910,
                "fourP17": 67798,
                "twoP17twop18": 69077,
                "fourP18": 72465,
                "twoP18": 68649,
                "normalTotwoP18Gain": "4,16",
                "twoP17twoP18Gain": "4,81",
                "combiTo4P18Gain": "4,90",
                "normalTo4P18Gain": "9,95",
                "fourP17ToCombiGain": "1,89",
                "fourP17ToFourP18Gain": "6,88"
            },
            {
                "dpsColor": 'yellow',
                "className": "Rogue",
                "spec": "Assassination",
                "token": "Vanquisher",
                "normalDps": 56022,
                "fourP17": 63280,
                "twoP17twop18": 66699,
                "fourP18": 65400,
                "twoP18": 61127,
                "normalTotwoP18Gain": "9,11",
                "twoP17twoP18Gain": "19,06",
                "combiTo4P18Gain": "-1,95",
                "normalTo4P18Gain": "16,74",
                "fourP17ToCombiGain": "5,40",
                "fourP17ToFourP18Gain": "3,35"
            },
            {
                "dpsColor": 'yellow',
                "className": "Rogue",
                "spec": "Combat",
                "token": "Vanquisher",
                "normalDps": 49008,
                "fourP17": 54179,
                "twoP17twop18": 53896,
                "fourP18": 55767,
                "twoP18": 52429,
                "normalTotwoP18Gain": "6,98",
                "twoP17twoP18Gain": "9,97",
                "combiTo4P18Gain": "3,47",
                "normalTo4P18Gain": "13,79",
                "fourP17ToCombiGain": "-0,52",
                "fourP17ToFourP18Gain": "2,93"
            },
            {
                "dpsColor": 'yellow',
                "className": "Rogue",
                "spec": "Subtlety",
                "token": "Vanquisher",
                "normalDps": 53249,
                "fourP17": 56079,
                "twoP17twop18": 58238,
                "fourP18": 60572,
                "twoP18": 56367,
                "normalTotwoP18Gain": "5,86",
                "twoP17twoP18Gain": "9,37",
                "combiTo4P18Gain": "4,01",
                "normalTo4P18Gain": "13,75",
                "fourP17ToCombiGain": "3,85",
                "fourP17ToFourP18Gain": "8,01"
            },
            {
                "dpsColor": '#ABD473',
                "className": "Hunter",
                "spec": "Beast Mastery",
                "token": "Protector",
                "normalDps": 52773,
                "fourP17": 58392,
                "twoP17twop18": 56882,
                "fourP18": 60993,
                "twoP18": 55739,
                "normalTotwoP18Gain": "5,62",
                "twoP17twoP18Gain": "7,79",
                "combiTo4P18Gain": "7,23",
                "normalTo4P18Gain": "15,58",
                "fourP17ToCombiGain": "-2,59",
                "fourP17ToFourP18Gain": "4,45"
            },
            {
                "dpsColor": '#ABD473',
                "className": "Hunter",
                "spec": "Marksmanship",
                "token": "Protector",
                "normalDps": 56461,
                "fourP17": 58892,
                "twoP17twop18": 58426,
                "fourP18": 67163,
                "twoP18": 57499,
                "normalTotwoP18Gain": "1,84",
                "twoP17twoP18Gain": "3,48",
                "combiTo4P18Gain": "14,95",
                "normalTo4P18Gain": "18,95",
                "fourP17ToCombiGain": "-0,79",
                "fourP17ToFourP18Gain": "14,04"
            },
            {
                "dpsColor": '#ABD473',
                "className": "Hunter",
                "spec": "Survival",
                "token": "Protector",
                "normalDps": 48515,
                "fourP17": 52917,
                "twoP17twop18": 55812,
                "fourP18": 57422,
                "twoP18": 51590,
                "normalTotwoP18Gain": "6,34",
                "twoP17twoP18Gain": "15,04",
                "combiTo4P18Gain": "2,88",
                "normalTo4P18Gain": "18,36",
                "fourP17ToCombiGain": "5,47",
                "fourP17ToFourP18Gain": "8,51"
            },
            {
                "dpsColor": '#00FF96',
                "className": "Monk",
                "spec": "1H Windwalker",
                "token": "Protector",
                "normalDps": 52076,
                "fourP17": 56408,
                "twoP17twop18": 57879,
                "fourP18": 59861,
                "twoP18": 56185,
                "normalTotwoP18Gain": "7,89",
                "twoP17twoP18Gain": "11,14",
                "combiTo4P18Gain": "3,42",
                "normalTo4P18Gain": "14,95",
                "fourP17ToCombiGain": "2,61",
                "fourP17ToFourP18Gain": "6,12"
            },
            {
                "dpsColor": '#00FF96',
                "className": "Monk",
                "spec": "2H Windwalker",
                "token": "Protector",
                "normalDps": 52576,
                "fourP17": 57057,
                "twoP17twop18": 58466,
                "fourP18": 60381,
                "twoP18": 56764,
                "normalTotwoP18Gain": "7,97",
                "twoP17twoP18Gain": "11,20",
                "combiTo4P18Gain": "3,28",
                "normalTo4P18Gain": "14,85",
                "fourP17ToCombiGain": "2,47",
                "fourP17ToFourP18Gain": "5,83"
            },
            {
                "dpsColor": '#C79C6E',
                "className": "Warrior",
                "spec": "Arms",
                "token": "Protector",
                "normalDps": 48614,
                "fourP17": 51156,
                "twoP17twop18": 52139,
                "fourP18": 64470,
                "twoP18": 51140,
                "normalTotwoP18Gain": "5,20",
                "twoP17twoP18Gain": "7,25",
                "combiTo4P18Gain": "23,65",
                "normalTo4P18Gain": "32,62",
                "fourP17ToCombiGain": "1,92",
                "fourP17ToFourP18Gain": "26,03"
            },
            {
                "dpsColor": '#C79C6E',
                "className": "Warrior",
                "spec": "1H Fury",
                "token": "Protector",
                "normalDps": 60269,
                "fourP17": 65734,
                "twoP17twop18": 63762,
                "fourP18": 66650,
                "twoP18": 62849,
                "normalTotwoP18Gain": "4,28",
                "twoP17twoP18Gain": "5,80",
                "combiTo4P18Gain": "4,53",
                "normalTo4P18Gain": "10,59",
                "fourP17ToCombiGain": "-3,00",
                "fourP17ToFourP18Gain": "1,39"
            },
            {
                "dpsColor": '#C79C6E',
                "className": "Warrior",
                "spec": "2H Fury",
                "token": "Protector",
                "normalDps": 60914,
                "fourP17": 65770,
                "twoP17twop18": 64282,
                "fourP18": 67380,
                "twoP18": 63317,
                "normalTotwoP18Gain": "3,94",
                "twoP17twoP18Gain": "5,53",
                "combiTo4P18Gain": "4,82",
                "normalTo4P18Gain": "10,61",
                "fourP17ToCombiGain": "-2,26",
                "fourP17ToFourP18Gain": "2,45"
            },
            {
                "dpsColor": '#C79C6E',
                "className": "Warrior",
                "spec": "Gladiator",
                "token": "Protector",
                "normalDps": 54990,
                "fourP17": 59046,
                "twoP17twop18": 61071,
                "fourP18": 61618,
                "twoP18": 60339,
                "normalTotwoP18Gain": "9,73",
                "twoP17twoP18Gain": "11,06",
                "combiTo4P18Gain": "0,90",
                "normalTo4P18Gain": "12,05",
                "fourP17ToCombiGain": "3,43",
                "fourP17ToFourP18Gain": "4,36"
            },
            {
                "dpsColor": '#0070DE',
                "className": "Shaman",
                "spec": "Elemental",
                "token": "Protector",
                "normalDps": 52649,
                "fourP17": 57180,
                "twoP17twop18": 57331,
                "fourP18": 60245,
                "twoP18": 55261,
                "normalTotwoP18Gain": "4,96",
                "twoP17twoP18Gain": "8,89",
                "combiTo4P18Gain": "5,08",
                "normalTo4P18Gain": "14,43",
                "fourP17ToCombiGain": "0,26",
                "fourP17ToFourP18Gain": "5,36"
            },
            {
                "dpsColor": '#0070DE',
                "className": "Shaman",
                "spec": "Enhancement",
                "token": "Protector",
                "normalDps": 49124,
                "fourP17": 52093,
                "twoP17twop18": 52780,
                "fourP18": 55643,
                "twoP18": 51946,
                "normalTotwoP18Gain": "5,74",
                "twoP17twoP18Gain": "7,44",
                "combiTo4P18Gain": "5,42",
                "normalTo4P18Gain": "13,27",
                "fourP17ToCombiGain": "1,32",
                "fourP17ToFourP18Gain": "6,81"
            },
            {
                "dpsColor": '#F58CBA',
                "className": "Paladin",
                "spec": "Retribution",
                "token": "Conqueror",
                "normalDps": 55148,
                "fourP17": 55912,
                "twoP17twop18": 61404,
                "fourP18": 64482,
                "twoP18": 60487,
                "normalTotwoP18Gain": "9,68",
                "twoP17twoP18Gain": "11,34",
                "combiTo4P18Gain": "5,01",
                "normalTo4P18Gain": "16,93",
                "fourP17ToCombiGain": "9,82",
                "fourP17ToFourP18Gain": "15,33"
            },
            {
                "dpsColor": 'purple',
                "className": "Warlock",
                "spec": "Affliction",
                "token": "Conqueror",
                "normalDps": 53602,
                "fourP17": 60379,
                "twoP17twop18": 59098,
                "fourP18": 58841,
                "twoP18": 55323,
                "normalTotwoP18Gain": "3,21",
                "twoP17twoP18Gain": "10,25",
                "combiTo4P18Gain": "-0,43",
                "normalTo4P18Gain": "9,77",
                "fourP17ToCombiGain": "-2,12",
                "fourP17ToFourP18Gain": "-2,55"
            },
            {
                "dpsColor": 'purple',
                "className": "Warlock",
                "spec": "Demonology",
                "token": "Conqueror",
                "normalDps": 54015,
                "fourP17": 56992,
                "twoP17twop18": 57562,
                "fourP18": 59469,
                "twoP18": 56389,
                "normalTotwoP18Gain": "4,40",
                "twoP17twoP18Gain": "6,57",
                "combiTo4P18Gain": "3,31",
                "normalTo4P18Gain": "10,10",
                "fourP17ToCombiGain": "1,00",
                "fourP17ToFourP18Gain": "4,35"
            },
            {
                "dpsColor": 'purple',
                "className": "Warlock",
                "spec": "Destruction",
                "token": "Conqueror",
                "normalDps": 57126,
                "fourP17": 63003,
                "twoP17twop18": 64500,
                "fourP18": 65651,
                "twoP18": 62218,
                "normalTotwoP18Gain": "8,91",
                "twoP17twoP18Gain": "12,91",
                "combiTo4P18Gain": "1,78",
                "normalTo4P18Gain": "14,92",
                "fourP17ToCombiGain": "2,38",
                "fourP17ToFourP18Gain": "4,20"
            },
            {
                "dpsColor": 'gray',
                "className": "Priest",
                "spec": "AS Shadow",
                "token": "Conqueror",
                "normalDps": 49568,
                "fourP17": 52340,
                "twoP17twop18": 53031,
                "fourP18": 56546,
                "twoP18": 52720,
                "normalTotwoP18Gain": "6,36",
                "twoP17twoP18Gain": "6,99",
                "combiTo4P18Gain": "6,63",
                "normalTo4P18Gain": "14,08",
                "fourP17ToCombiGain": "1,32",
                "fourP17ToFourP18Gain": "8,04"
            },
            {
                "dpsColor": 'gray',
                "className": "Priest",
                "spec": "CoP Shadow",
                "token": "Conqueror",
                "normalDps": 55194,
                "fourP17": 59238,
                "twoP17twop18": 58436,
                "fourP18": 60207,
                "twoP18": 56306,
                "normalTotwoP18Gain": "2,01",
                "twoP17twoP18Gain": "5,87",
                "combiTo4P18Gain": "3,03",
                "normalTo4P18Gain": "9,08",
                "fourP17ToCombiGain": "-1,35",
                "fourP17ToFourP18Gain": "1,64"
            },
            {
                "dpsColor": 'gray',
                "className": "Priest",
                "spec": "VE Shadow",
                "token": "Conqueror",
                "normalDps": 47554,
                "fourP17": 49442,
                "twoP17twop18": 51205,
                "fourP18": 54377,
                "twoP18": 50738,
                "normalTotwoP18Gain": "6,70",
                "twoP17twoP18Gain": "7,68",
                "combiTo4P18Gain": "6,19",
                "normalTo4P18Gain": "14,35",
                "fourP17ToCombiGain": "3,57",
                "fourP17ToFourP18Gain": "9,98"
            }
        ]
    );


    self.filteredSpecs = ko.observableArray(self.specs());
    self.gainChart = null;
    self.dpsChart = null;


    function generateCharts() {
        self.gainChart = createGainCharts();
        self.dpsChart = createDpsCharts();
    }

    var reloadCharts = function () {
        dynatable.records.resetOriginal();
        dynatable.queries.run();
        var ts = dynatable.records.sort();
        self.gainChart = createDpsCharts(ts);
        self.dpsChart = createGainCharts(ts);
    };

    function filterSpecsByToken() {
        var filteredSpecs = [];
        self.specs()
            .forEach(function (spec) {
                if ($.inArray(spec.token, self.chosenTokens()) != -1) {
                    filteredSpecs.push(spec);
                }
            });
        self.filteredSpecs(filteredSpecs);
        dynatable.settings.dataset.originalRecords = filteredSpecs;
        dynatable.process();
        reloadCharts();
        return filteredSpecs;
    }

    self.tokenSelected = function () {
        filterSpecsByToken();
    };

    var tokenTypes = ["Vanquisher", "Protector", "Conqueror"];
    self.availableTokens = ko.observableArray(tokenTypes);
    self.chosenTokens = ko.observableArray(tokenTypes);

    var getGainData = function () {
        var dpsColumns = [];
        self.filteredSpecs()
            .forEach(function (spec) {
                dpsColumns.push(
                    [
                        spec.className + " " +
                        spec.spec,
                        parseFloat(spec.normalTotwoP18Gain.replace(',', '.')),
                        parseFloat(spec.twoP17twoP18Gain.replace(',', '.')),
                        parseFloat(spec.combiTo4P18Gain.replace(',', '.')),
                        parseFloat(spec.normalTo4P18Gain.replace(',', '.')),
                        parseFloat(spec.fourP17ToCombiGain.replace(',', '.')),
                        parseFloat(spec.fourP17ToFourP18Gain.replace(',', '.'))
                    ]
                )
            });
        return dpsColumns;
    };

    var createGainCharts = function (gainData) {
        var myGainData = [];
        var dpsColors = [];
        if (gainData == undefined || gainData == null) {
            myGainData = getGainData();
            dpsColors = getDpsColors();
        } else {
            gainData.forEach(function (spec) {
                myGainData.push(
                    [spec.className + " " + spec.spec,
                        parseFloat(spec.normalTotwoP18Gain.replace(',', '.')),
                        parseFloat(spec.twoP17twoP18Gain.replace(',', '.')),
                        parseFloat(spec.combiTo4P18Gain.replace(',', '.')),
                        parseFloat(spec.normalTo4P18Gain.replace(',', '.')),
                        parseFloat(spec.fourP17ToCombiGain.replace(',', '.')),
                        parseFloat(spec.fourP17ToFourP18Gain.replace(',', '.'))
                    ]
                )
                dpsColors.push(spec.dpsColor);
            });
        }
        return c3.generate({
            bindto: "#dpsChart",
            size: {
                height: 600,
            },
            data: {
                columns: myGainData,
                type: 'spline'
            },
            color: {
                pattern: dpsColors
            },
            grid: {
                y: {
                    lines: [
                        { value: 0, text: "breakeven"}
                    ]
                }
            },
            zoom: {
                enabled: true
            },
            axis: {
                x: {
                    type: 'category',
                    categories: ['T18 2P', '2P+2P', '2P+2P to T18 4P', '0P to 4P T18', 'T17 4P to 2P+2P', '4P T17 to 4P T18']
                }
            },
            tooltip: {
                grouped: false // Default true
            }
        });
    };

    var getDpsData = function () {
        var dpsColumns = [];
        self.filteredSpecs()
            .forEach(function (spec) {
                dpsColumns.push(
                    [spec.className + " " + spec.spec, spec.normalDps, spec.fourP17, spec.twoP17twop18, spec.fourP18, spec.twoP18]
                )
            });
        return dpsColumns;
    };

    var getDpsColors = function() {
        var dpsColors = [];
        self.filteredSpecs()
            .forEach(function (spec) {
                dpsColors.push(
                    [spec.dpsColor]
                )
            });
        return dpsColors;
    };

    var createDpsCharts = function (dpsData) {
        var myDpsData = [];
        var dpsColors = [];
        if (dpsData == undefined || dpsData == null) {
            myDpsData = getDpsData();
            dpsColors = getDpsColors();
        } else {
            dpsData.forEach(function (spec) {
                myDpsData.push(
                    [spec.className + " " + spec.spec, spec.normalDps, spec.fourP17, spec.twoP17twop18, spec.fourP18, spec.twoP18]
                );
                dpsColors.push(spec.dpsColor);
            });
        }

        return c3.generate({
            bindto: "#gainChart",
            data: {
                columns: myDpsData,
                type: 'bar'
            },
            color: {
                pattern: dpsColors
            },
            axis: {
                x: {
                    type: 'category',
                    categories: ['0P', '4P T17', '2P T17 + 2P T18', '4P T18', '2P T18']
                },
                y: {
                    max: 80000,
                    min: 40000
                }
            },
            tooltip: {
                grouped: false // Default true
            }
        });
    };
    generateCharts();

    var table = $('#myTable');


    var dynatable = table.dynatable({
        dataset: {
            records: self.filteredSpecs()
        }
    }).data("dynatable");

    table.bind('dynatable:afterProcess', function (e, dynatable) {
        reloadCharts();
    });
};


var tier18LootDistribution = new Tier18LootDistribution();
ko.applyBindings(tier18LootDistribution, $("#page-inner")[0]);

