package main

import (
	"fmt"
	"log"
	"math/rand"
	"net/http"
	"net/smtp"
	"strconv"
	"time"
)

func main() {
	mux := http.NewServeMux()
	mux.HandleFunc("/", func(res http.ResponseWriter, req *http.Request) {
		// POST 방식
		// req.ParseForm()
		// random := makeRandom()
		// fmt.Println(req)
		// fmt.Println(req.FormValue("address"))
		// sendEmail(req.FormValue("address"), random)
		// res.Header().Set("Access-Control-Allow-Origin", "*")
		// res.Write([]byte(random))

		//GET방식
		fmt.Println(req)
		req.ParseForm()
		random := makeRandom()
		fmt.Println(req)
		fmt.Println(req.URL.Query()["address"])
		sendEmail(req.URL.Query()["address"][0], random)
		res.Header().Set("Access-Control-Allow-Origin", "*")
		res.Write([]byte(random))

	})

	http.ListenAndServe(":3000", mux)
}

func makeRandom() string {
	randAuth := ""
	authLen := 6
	time := rand.NewSource(time.Now().UnixNano())
	random := rand.New(time)

	for i := 0; i < authLen; i++ {
		randAuth += strconv.Itoa(random.Intn(10))
	}

	return randAuth
}

func sendEmail(dest, body string) {
	from := "chosm144@gmail.com"
	pass := "chosm0819@"
	to := dest

	msg := "From: " + from + "\n" +
		"To: " + to + "\n" +
		"Subject: (noreply)5G는 5조 이메일 인증 번호 입니다.\n\n" +
		"인증번호: " + body

	err := smtp.SendMail("smtp.gmail.com:587",
		smtp.PlainAuth("", from, pass, "smtp.gmail.com"),
		from, []string{to}, []byte(msg))

	if err != nil {
		log.Printf("smtp error: %s", err)
		return
	}
	log.Println(msg)
}
