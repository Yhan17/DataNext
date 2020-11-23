CREATE FUNCTION public.inser_into_situacao()
    RETURNS trigger
    LANGUAGE 'plpgsql'
     NOT LEAKPROOF
AS $BODY$ BEGIN
 insert into situacao(quantidadearmazenada,status,armazem_id) values('0','1',new.id);
 return NEW;
 END$BODY$;

ALTER FUNCTION public.inser_into_situacao()
    OWNER TO topicos2;

CREATE TRIGGER insert_into_situacao AFTER INSERT ON armazem
  FOR EACH ROW EXECUTE PROCEDURE insert_into_situacao();


CREATE FUNCTION public.update_situacao()
    RETURNS trigger
    LANGUAGE 'plpgsql'
     NOT LEAKPROOF
AS $BODY$
DECLARE
capacidade double precision;
quantidadearmazenada double precision;
results double precision;
begin
	SELECT into capacidade armazem.capacidade from armazem where id = new.armazem_id;
	SELECT into quantidadearmazenada situacao.quantidadearmazenada from situacao where armazem_id = new.armazem_id;
	SELECT into results (quantidadearmazenada - new.quantidade);
	
	if (quantidadearmazenada - new.quantidade) < 0 THEN
		update situacao set quantidadearmazenada = 0, status = 1 where situacao.armazem_id = new.armazem_id;
	end if;
	
	if (quantidadearmazenada - new.quantidade) > 0 THEN
		update situacao set quantidadearmazenada = results where armazem_id = new.armazem_id;
		if (((capacidade - quantidadearmazenada)/capacidade) > 0.5 AND ((capacidade - quantidadearmazenada)/capacidade) < capacidade ) then
			update situacao set status = 3 where situacao.armazem_id = new.armazem_id;
		end if;
		if (((capacidade - quantidadearmazenada)/capacidade) < 0.5 AND ((capacidade - quantidadearmazenada)/capacidade) > 0 ) then
			update situacao set status = 2 where situacao.armazem_id = new.armazem_id;
		end if;
	end if;
	
return NEW;

end$BODY$;

ALTER FUNCTION public.update_situacao()
    OWNER TO topicos2;




CREATE TRIGGER update_situacao AFTER INSERT ON armazenargrao
  FOR EACH ROW EXECUTE PROCEDURE update_situacao();




CREATE FUNCTION public.update_situacao_remove()
    RETURNS trigger
    LANGUAGE 'plpgsql'
     NOT LEAKPROOF
AS $BODY$

DECLARE
capacidade double precision;
quantidadearmazenada double precision;
results double precision;
begin
	SELECT into capacidade armazem.capacidade from armazem where id = new.armazem_id;
	SELECT into quantidadearmazenada situacao.quantidadearmazenada from situacao where armazem_id = new.armazem_id;
	SELECT into results quantidadearmazenada + new.quantidadearmazenada;
	
	if( capacidade - quantidadearmazenada ) < new.quantidadearmazenada THEN
		update situacao set quantidadearmazenada = capacidade,status=4 where situacao.armazem_id = new.armazem_id;
	end if;
	
	if (capacidade - quantidadearmazenada) > new.quantidadearmazenada THEN
		update situacao set quantidadearmazenada = results where situacao.armazem_id = new.armazem_id;
		if (((capacidade - quantidadearmazenada)/capacidade) > 0.5 AND ((capacidade - quantidadearmazenada)/capacidade) < capacidade ) then
			update situacao set status = 3 where situacao.armazem_id = new.armazem_id;
		end if;
		if (((capacidade - quantidadearmazenada)/capacidade) < 0.5 AND ((capacidade - quantidadearmazenada)/capacidade) > 0 )then
			update situacao set status = 2 where situacao.armazem_id = new.armazem_id;
		end if;
	end if;
	
return NEW;

END$BODY$;

ALTER FUNCTION public.update_situacao_remove()
    OWNER TO topicos2;



CREATE TRIGGER update_situacao_remove AFTER INSERT ON retirargrao
  FOR EACH ROW EXECUTE PROCEDURE update_situacao_remove();