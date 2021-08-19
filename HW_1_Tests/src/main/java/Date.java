    /**
     * <h1> Класс Date </h1>
     * Класс содержит метод printNextDay, который вычисляет дату следующего дня.
     */
    public class Date {

        private Date() {
        }

        /**
         * <h2> Метод printNextDay </h2>
         * Метод вычисляет дату следующего дня.
         * @param day Целочисленное значение, соответствующее номеру дня недели.
         * @param month Целочисленное значение, соответствующее номеру месяца.
         * @param year Целочисленное значение, соответсвующее году.
         * @return Метод возвращает строку с датой следующего дня.
         */
        public static String printNextDate(Integer day,Integer month,Integer year){
            if (day <= 27) {
                return (day+1)+" "+month+" "+year;
            }
            else if (day==28) {
                if (month != 2) {
                    return (day+1)+" "+month+" "+year;
                } else if (year%4==0) {
                    return (day+1)+" "+month+" "+year;
                } else {
                    return 1+" "+(month+1)+" "+year;
                }
            }
            else if(day==29){
                if(month!=2){
                    return (day+1)+" "+month+" "+year;
                } else {
                    return 1+" "+(month+1)+" "+year;
                }
            }
            else if (day==30){
                if (month==4||month==6||month==9||month==11){
                    return 1+" "+(month+1)+" "+year;
                }else {
                    return (day+1)+" "+month+" "+year;
                }
            }
            else {
                if(month!=12){
                    return 1+" "+(month+1)+" "+year;
                } else {
                    return 1+" "+1+" "+(year+1);
                }
            }
        }
    }
