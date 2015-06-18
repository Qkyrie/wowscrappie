/*var chart = c3.generate({
 bindto: '#chart',
 data: {
 columns: [
 ['0set', 66283, 59218],
 ['2set', 70008, 63221],
 ['4set', 78615, 70174]
 ],
 type: 'bar',
 groups: [
 ['0set', '2set', '4set']
 ]
 },
 grid: {
 y: {
 lines: [{value:0}]
 }
 }
 });*/

var Tier18LootDistribution = function () {

    var self = this;

    this.specs = ko.observableArray([
        {
            className: "Death Knight",
            spec: "Frost 1H",
            token: "Vanquisher",
            normalDps: 66283,
            twoPiece: 70008,
            fourPiece: 78615,
            normalToTwo: 5.62,
            twoToFour: 12.29,
            totalGain: 18.61
        },
        {
            className: "Death Knight",
            spec: "Frost 2H",
            token: "Vanquisher",
            normalDps: 59218,
            twoPiece: 63221,
            fourPiece: 70174,
            normalToTwo: 6.76,
            twoToFour: 11.00,
            totalGain: 18.50
        },
        {
            className: "Death Knight",
            spec: "Unholy",
            token: "Vanquisher",
            normalDps: 56112,
            twoPiece: 56350,
            fourPiece: 63830,
            normalToTwo: 0.42,
            twoToFour: 13.27,
            totalGain: 13.75
        },
        {
            className: "Druid",
            spec: "Balance",
            token: "Vanquisher",
            normalDps: 54642,
            twoPiece: 60242,
            fourPiece: 63916,
            normalToTwo: 10.25,
            twoToFour: 6.10,
            totalGain: 16.97
        },
        {
            className: "Druid",
            spec: "Feral",
            token: "Vanquisher",
            normalDps: 57755,
            twoPiece: 61836,
            fourPiece: 67764,
            normalToTwo: 7.07,
            twoToFour: 9.59,
            totalGain: 17.33
        },
        {
            className: "Hunter",
            spec: "Beast Mastery",
            token: "Protector",
            normalDps: 52317,
            twoPiece: 54629,
            fourPiece: 59514,
            normalToTwo: 4.42,
            twoToFour: 8.94,
            totalGain: 13.76
        },
        {
            className: "Hunter",
            spec: "Marksmanship",
            token: "Protector",
            normalDps: 57471,
            twoPiece: 58906,
            fourPiece: 65389,
            normalToTwo: 2.50,
            twoToFour: 11.01,
            totalGain: 13.78
        },
        {
            className: "Hunter",
            spec: "Survival",
            token: "Protector",
            normalDps: 52554,
            twoPiece: 55812,
            fourPiece: 57422,
            normalToTwo: 6.20,
            twoToFour: 2.88,
            totalGain: 9.26
        }
    ]);
};


var tier18LootDistribution = new Tier18LootDistribution();
ko.applyBindings(tier18LootDistribution, $("#page-inner")[0]);

$('#myTable').dataTable();

/**
 *
---TODO---
 Mage    Arcane    Vanquisher    62945    70335    74099    11.74%    5.35%    17.72%
 Mage    Fire    Vanquisher    52026    55103    58130    5.91%    5.49%    11.73%
 Mage    Frost    Vanquisher    62567    66321    68190    6.00%    2.82%    8.99%
 Monk    1H Windwalker    Protector    52685    57787    60878    9.68%    5.35%    15.55%
 Monk    2H Windwalker    Protector    53357    58439    61647    9.52%    5.49%    15.54%
 Paladin    Retribution    Conqueror    60149    64200    69299    6.73%    7.94%    15.21%
 Priest    AS Shadow    Conqueror    49831    52134    55299    4.62%    6.07%    10.97%
 Priest    CoP Shadow    Conqueror    54877    55956    58935    1.97%    5.32%    7.39%
 Priest    VE Shadow    Conqueror    47672    52570    55518    10.27%    5.61%    16.46%
 Rogue    Assassination    Vanquisher    54590    57020    61625    4.45%    8.08%    12.89%
 Rogue    Combat    Vanquisher    49240    52682    55816    6.99%    5.95%    13.35%
 Rogue    Subtelty    Vanquisher    56707    58293    61295    2.80%    5.15%    8.09%
 Shaman    Elemental    Protector    51030    53450    57722    4.74%    7.99%    13.11%
 Shaman    Enhancement    Protector    48557    51268    56957    5.58%    11.10%    17.30%
 Warlock    Affliction    Conqueror    55054    62816    68088    14.10%    8.39%    23.67%
 Warlock    Demonology    Conqueror    51149    54081    57283    5.73%    5.92%    11.99%
 Warlock    Destruction    Conqueror    51140    55075    58140    7.69%    5.57%    13.69%
 Warrior    Arms    Protector    52727    53405    58330    1.29%    9.22%    10.63%
 Warrior    1H Fury    Protector    66556    68530    71427    2.97%    4.23%    7.32%
 Warrior    2H Fury    Protector    64305    66297    69338    3.10%    4.59%    7.83%
 Warrior    Gladiator    Protector    57269    62754    64292    9.58%    2.45%    12.26%


---DONE---

 *              Class    Spec    Token    0p DPS    2p DPS    4p DPS    0-2pc gain    2-4pc gain    0-4pc gain
 Death Knight    1H Frost    Vanquisher    66283    70008    78615    5.62%    12.29%    18.61%
 Death Knight    2H Frost    Vanquisher    59218    63221    70174    6.76%    11.00%    18.50%
 Death Knight    Unholy    Vanquisher    56112    56350    63830    0.42%    13.27%    13.75%
 Druid    Balance    Vanquisher    54642    60242    63916    10.25%    6.10%    16.97%
 Druid    Feral    Vanquisher    57755    61836    67764    7.07%    9.59%    17.33%
 Hunter    Beast Mastery    Protector    52317    54629    59514    4.42%    8.94%    13.76%
 Hunter    Marksmanship    Protector    57471    58906    65389    2.50%    11.01%    13.78%
 Hunter    Survival    Protector    52554    55812    57422    6.20%    2.88%    9.26%
 */
